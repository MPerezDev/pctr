/**
 * Ejercicio 1 - Práctica 7
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase prodCon que se basa en un monitor con sincornización wait() - notifyAll().
 */
public class prodCon{

    /**
     * Variable que almacena el número de elementos del buffer.
     */
    private static int tamBuffer = 10;

    /**
     * Variable que ejerce de buffer.
     */
    private static int buffer[] = new int[tamBuffer];

    /**
     * Variable que guarda la posición en la que se va a insertar el siguiente elemento.
     */
    public static int in_Ptr = 0;

    /**
     * Variable que guarda la posición en la que se va a extraer el siguiente elemento.
     */
    private static int out_Ptr = 0;

    /**
     * Variable auxiliar que ejerce de contador.
     */
    private static int cont = 0;

    /**
     * Constructor de la clase.
     */
    public prodCon(){}

    /**
     * Método que inserta un elemento en el buffer.
     * @throws InterruptedException
     */
    public synchronized void producir() throws InterruptedException {
        while(cont == tamBuffer){
            wait();
        }       
        buffer[in_Ptr] = (int)(Math.random()*100);
        System.out.println("Produciendo" + buffer[in_Ptr] + " en la posicion " + in_Ptr);
        in_Ptr = (in_Ptr + 1) % tamBuffer;
        cont++;
        notifyAll();

    }

    /**
     * Método que extrae un elemento del buffer.
     * @throws InterruptedException
     */
    public synchronized void consumir() throws InterruptedException {
        while(cont == 0){
            wait();
        }
        System.out.println("Consumiendo" + buffer[out_Ptr] + " en la posicion " + out_Ptr);
        out_Ptr = (out_Ptr + 1) % tamBuffer;
        cont--;
        notifyAll();
    }

}
