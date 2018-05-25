package hojatrabajo11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author javie
 */
public class HojaTrabajo11 {
    
    public static void main(String[] args) throws IOException {
        
        File archivo = new File ("guategrafo.txt");
        ArrayList<Ciudades> ciudad = new ArrayList<>();
        ArrayList<String> ciudades = new ArrayList<>();
        Ciudades city = new Ciudades();
        

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
        
        
        
        Scanner sc = new Scanner(System.in);
        int op = 0;
        int seleccion;
        
        AlgoritmoFloyd af = new AlgoritmoFloyd();
        long matriz[][];
        //System.out.println(af.algoritmoFloyd(matriz, ciudades, ciudad));
        OUTER:
        while (true) {
            System.out.println("1. Mostrar ruta mas corta entre ciudades.");
            System.out.println("2. Indicar ciudad que esta en el centro del grafo.");
            System.out.println("3. Modificar grafo.");
            System.out.println("4. Salir");
            seleccion = sc.nextInt();
            
            switch (seleccion) {
                case 1:
                    System.out.println("\t1. Ingrese la ciudad de origen.");
                    String o = sc.nextLine();
                    o = sc.nextLine();
                    System.out.println("\t2. Ingrese la ciudad de destino.");
                    String d = sc.nextLine();
                    
                    ciudades.clear();
                    ciudades = city.crearLista(ciudad);
                    matriz = af.crearMatriz(ciudades, ciudad);
                    
                    if(af.verificarExistencia(o, d, ciudades)){
                        System.out.println(af.algoritmoFloyd(matriz, ciudades, o, d));
                        
                    }else{
                        System.out.println("Dichas ciudades no estan contenidas en GuateGrafo.");
                    }
                    
                    
                    break;
                case 2:
                    ciudades.clear();
                    ciudades = city.crearLista(ciudad);
                    matriz = af.crearMatriz(ciudades, ciudad);
                    System.out.println(af.verMatriz(matriz));
                    System.out.println("\n----");
                    af.centerGraph(matriz);
                    
                    System.out.println("La ciudad central es: " + ciudades.get(af.centerGraph(matriz)));
                    
                    break;
                case 3:
                    String seleccion2 = "";
                    System.out.println("\ta. Hay interrupción de tráfico entre un par de ciudades");
                    System.out.println("\tb. Establecer nueva conexion entre ciudades.");
                    seleccion2 = sc.next();
                    
                    if("a".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese la ciudad de origen.");
                        String ori = sc.next();
                        System.out.println("Ingrese la ciudad de destino.");
                        String dest = sc.next();
                        
                        boolean hubo = false;
                        for(Ciudades c: ciudad){
                            if((c.getOrigen().equals(ori)) && (c.getDestino().equals(dest))){
                                ciudad.remove(c);
                                hubo = true;
                                System.out.println("Se ha establecido la interrupcion correctamente.");
                                break;
                            }
                        }
                        if(hubo == false){
                            System.out.println("No se pudo encontrar dichas ciudades.");
                        }
                    }else if("b".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingrese la ciudad de origen.");
                        String ori = sc.next();
                        System.out.println("Ingrese la ciudad de destino.");
                        String dest = sc.next();
                        System.out.println("Ingrese la distancia entre " + ori + " y " + dest);
                        int dist = sc.nextInt();
                        
                        ciudad.add(new Ciudades(ori, dest, dist));
                        System.out.println("Se agrego las ciudades y su distancia correctamente.");
                    }
                    
                    break;
                case 4:
                    break OUTER;
                default:
                    break;
            }
            
            
            System.out.println("--------------------------------------------------------");
        }
    }
}