/**
 * Ejercicio 1 - Práctica 8
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase lectorEscritor que se basa en un monitor con sincronización wait() - notifyAll().
 */
public class lectorEscritor {
    
    /**
     * Variable que almacena el número de lectores que están leyendo.
     */
    private static int lectores = 0;

    /**
     * Variable que indica si hay un escritor activo.
     */
    private static boolean escribiendo = false;

    /**
     * Constructor de la clase.
     */
    public lectorEscritor(){}

    /**
     * Método que simula la lectura de un recurso.
     * @throws InterruptedException
     */
    public synchronized void iniciaLectura() throws InterruptedException{
        while(escribiendo){
            wait();
        }
        lectores++;
        notifyAll();
    }

    /**
     * Método que simula la finalización de la lectura de un recurso.
     * @throws InterruptedException
     */
    public synchronized void acabarLectura() throws InterruptedException{
        lectores--;
        if(lectores == 0){
            notifyAll();
        }
    }

    /**
     * Método que simula la escritura de un recurso.
     * @throws InterruptedException
     */
    public synchronized void iniciarEscritura() throws InterruptedException{
        while(lectores > 0 || escribiendo){
            wait();
        }
        escribiendo = true;
    
    }

    /**
     * Método que simula la finalización de la escritura de un recurso.
     * @throws InterruptedException
     */
    public synchronized void acabarEscritura() throws InterruptedException{
        escribiendo = false;
        notifyAll();
    }


}
