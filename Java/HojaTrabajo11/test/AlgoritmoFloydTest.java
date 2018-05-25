
import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlgoritmoFloydTest {
    
    public AlgoritmoFloydTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of algoritmoFloyd method, of class AlgoritmoFloyd.
     */
    @Test
    public void testAlgoritmoFloyd() throws IOException {
        System.out.println("algoritmoFloyd");
        AlgoritmoFloyd instance = new AlgoritmoFloyd();
        Ciudades c = new Ciudades();
        ArrayList<Ciudades> city = instance.crearListado();
        ArrayList<String> ciudad = c.crearLista(city);
        long[][] matriz = instance.crearMatriz(ciudad, city);
        String origen = "Guatemala";
        String destino = "Coban";
        
        String expResult = "\nDe Guatemala ---> Coban debe irse por: Guatemala, Antigua, Mazatenango, Retalhuleu, Huehuetenango, Coban\n";
        String result = instance.algoritmoFloyd(matriz, ciudad, origen, destino);
        assertEquals(expResult, result);
    }

    /**
     * Test of centerGraph method, of class AlgoritmoFloyd.
     */
    @Test
    public void testCenterGraph() throws IOException {
        System.out.println("centerGraph");
        AlgoritmoFloyd instance = new AlgoritmoFloyd();
        Ciudades c = new Ciudades();
        ArrayList<Ciudades> city = instance.crearListado();
        ArrayList<String> ciudad = c.crearLista(city);
        long[][] matriz = instance.crearMatriz(ciudad, city);
        
        
        //Con operaciones realizadas previamente, se concluyo que el centro del grafo del documento existente es Huehuetenango
        int expResult = ciudad.indexOf("Huehuetenango");
        int result = instance.centerGraph(matriz);
        assertEquals(expResult, result);
    }
    
}
