
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlgoritmoFloyd {

    public AlgoritmoFloyd() {
        
    }
    
    public String algoritmoFloyd(long[][] matriz, ArrayList<String> ciudad, String origen, String destino){
        int vertices = matriz.length;
        long mAdyacencia[][] = matriz;
        
        int restriccion1 = ciudad.indexOf(origen);
        int restriccion2 = ciudad.indexOf(destino);
        
        String caminos[][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        String caminoRecorrido = "";
        String cadena = "", caminitos = "";
        
        int i, j, k;
        float temporal1, temporal2, temporal3, temporal4, minimo;
        
        for(i = 0; i < vertices; i++){
            for(j = 0; j < vertices; j++){
                caminos[i][j] = "";
                caminosAuxiliares[i][j] = "";
            }
        }
        
        for (k = 0; k < vertices; k++) {
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {
                    temporal1 = mAdyacencia[i][j];
                    temporal2 = mAdyacencia[i][k];
                    temporal3 = mAdyacencia[k][j];
                    temporal4 = temporal2 + temporal3;
                    
                    minimo = Math.min(temporal1, temporal4);
                    if(temporal1 != temporal4){
                        if(minimo == temporal4){
                            caminoRecorrido = "";
                            caminosAuxiliares[i][j] = k + "";
                            caminos[i][j] = caminosR(i, k, caminosAuxiliares, caminoRecorrido) + (k + 1);                            
                        }
                    }
                    mAdyacencia[i][j] = (long) minimo;
                }
            }
        }
        
        ArrayList<Integer> camino = new ArrayList<>();
        
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                if(mAdyacencia[i][j] != 1000000000){
                    if(i != j){
                        if(caminos[i][j].equals("") && (j == restriccion2) && (i == restriccion1)){
                            caminitos += "De " + ciudad.get(i) + " ---> " + ciudad.get(j) + " debe irse por: " + ciudad.get(i) + ", " + ciudad.get(j) + "\n";
                        }else if(!caminos[i][j].equals("") && (j == restriccion2) && (i == restriccion1)){
                            String demas = caminos[i][j];
                            //System.out.println("Todo: " + demas + " y " + ciudad.get(Integer.parseInt(demas) - 1));
                            if(!demas.contains(",")){
                                camino.add(Integer.parseInt(demas));
                            }
                            while(demas.contains(",")){
                                String walk = demas.substring(0, demas.indexOf(","));
                                demas = demas.substring(demas.indexOf(",") + 2);
                                camino.add(Integer.parseInt(walk));
                                if(!demas.contains(",")){
                                    camino.add(Integer.parseInt(demas));
                                }
                            }
                            
                            String c = "";
                            for(Integer in: camino){
                                
                                c += ciudad.get(in - 1) + ", ";
                            }
                            caminitos += "De " + ciudad.get(i) + " ---> " + ciudad.get(j) + " debe irse por: " + ciudad.get(i) + ", " + c + ciudad.get(j) + "\n";
                        }
                    }
                }
            }
        }
        return "\n" + caminitos;
    }
    
    
    public String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido){
        if(caminosAuxiliares[i][k].equals("")){
            return "";
        }else{
            caminoRecorrido += caminosR(i, Integer.parseInt(caminosAuxiliares[i][k]), caminosAuxiliares, caminoRecorrido) + (Integer.parseInt(caminosAuxiliares[i][k]) + 1) + ", ";
            return caminoRecorrido;
        }
    }
    
    public long[][] crearMatriz(ArrayList<String> ciudad, ArrayList<Ciudades> cities){
        long matriz[][] = new long[ciudad.size()][ciudad.size()];
        
        for (int i = 0; i < ciudad.size(); i++) {
            for (int j = 0; j < ciudad.size(); j++) {
                if(i == j){
                    matriz[i][j] = 0;
                }else{
                    String origen = ciudad.get(i);
                    String destino = ciudad.get(j);
                    int distancia = 999999999;
                    for(Ciudades c: cities){
                        if(origen.equals(c.getOrigen()) && destino.equals(c.getDestino())){
                            distancia = c.getDistancia();
                        }
                    }
                    matriz[i][j] = distancia;
                }
            }
        }
        
        return matriz;
    }
    
    public boolean verificarExistencia(String origen, String destino, ArrayList<String> ciudad){
        
        boolean existencia;
        
        existencia = ciudad.contains(origen) && ciudad.contains(destino);
        
        return existencia;
    }
    
    public String verMatriz(long[][] matriz){
        
        int fila = matriz.length;
        
        String cadena = "";
        
        for(int x = 0; x < fila; x++){
            for(int y = 0; y < fila; y++){
//                if(y == 0){
//                    cadena += cities.get(x) + "\t\t\t";
//                }
                if(matriz[x][y]==999999999){
                    cadena += -1 + "\t";
                }else{
                    cadena += matriz[x][y] + "\t";
                }
                
            }
            cadena += "\n";
        }
        if(cadena.equals("")){
            cadena = "No hay ninguna matriz para mostrar.";
        }
        return cadena;
    }
    
    public int centerGraph(long[][] matriz){
        ArrayList<Long> suma = new ArrayList<>();
        ArrayList<Long> maximo = new ArrayList<>();
        long max;
        int cont = 0;
        
        while(cont != matriz.length){
            max = 0;
            for (int i = 0; i < matriz.length; i++) {
                suma.add(matriz[i][cont]);
            }
            for(Long l: suma){
                if((l <= 9999999) && (l != 0)){
                    if(l > max){
                        max = l;
                    }
                }
            }
            maximo.add(max);
            suma.clear();
            cont++;
        }
        
        
        max = 0;
        cont = 0;
        for(Long l: maximo){
            if(l > max){
                max = l;
            }
        }
        int pos = maximo.indexOf(max);
        
        long min = max;
        
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) && (matriz[i][pos]!=0)){
                if(matriz[i][pos] < min){
                    min = matriz[i][pos];
                }
            }
        }
        
        int resultado = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][pos] == min){
                resultado = i;
                break;
            }
        }
        
        return resultado;
    }
    
    public ArrayList<Ciudades> crearListado() throws FileNotFoundException, IOException{
        ArrayList<Ciudades> ciudad = new ArrayList<>();
        File archivo = new File ("guategrafo.txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br1 = new BufferedReader(fr);
        String linea = "";
        Scanner scanner = new Scanner(fr);
        
        String ciudad1;
        String ciudad2;
        int distancia;
        int cont = 0;
        
        while (scanner.hasNextLine()) {
            linea = scanner.nextLine();
            
            ciudad1 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            ciudad2 = linea.substring(0, linea.indexOf(" "));
            linea = linea.substring(linea.indexOf(" ") + 1, linea.length());
            
            distancia = Integer.parseInt(linea.substring(0, linea.length()));
            
            //System.out.println(ciudad1 + "-" + ciudad2 + "-" + distancia);
            cont++;
            
            ciudad.add(new Ciudades(ciudad1, ciudad2, distancia));
            
            fr.close();
            br1.close();
        }
        
        return ciudad;
    }
}
