
import java.util.ArrayList;

public class Ciudades {
    
    private String origen;
    private String destino;
    private int distancia;

    public Ciudades(String origen, String destino, int distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public Ciudades() {
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Origen:" + origen + ", destino: " + destino + ", distancia: " + distancia;
    }
    
    public ArrayList crearLista(ArrayList<Ciudades> ciudad){
        ArrayList<String> ciudades = new ArrayList<>();
        
        for(Ciudades c: ciudad){
            if(!ciudades.contains(c.getOrigen())){
                ciudades.add(c.getOrigen());
            }
            if(!ciudades.contains(c.getDestino())){
                ciudades.add(c.getDestino());
            }
        }
        
        return ciudades;
    }
}
