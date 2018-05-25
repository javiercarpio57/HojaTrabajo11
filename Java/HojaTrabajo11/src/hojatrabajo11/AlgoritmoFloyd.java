
package hojatrabajo11;

import java.util.ArrayList;

/**
 *
 * @author javie
 */
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
        
        if(ciudad.contains(origen) && ciudad.contains(destino)){
            existencia = true;
        }else{
            existencia = false;
        }
        
        return existencia;
    }
    
    public String verMatriz(long[][] matriz){
        
        int fila = matriz.length;
        
        String cadena = "";
        for(int x = 0; x < fila; x++){
            for(int y = 0; y < fila; y++){
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
}
