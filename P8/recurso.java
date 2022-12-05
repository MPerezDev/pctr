/**
 * Ejercicio 1 - Práctica 8
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase recurso que encapsula el recurso compartido.
  */
public class recurso {
    
    /**
     * Variable que representa el recurso compartido.
     */
    private static long n = 0;

    /**
     * Constructor de la clase.
     */
    recurso(){}

    /**
     * Método que incrementa el valor de la variable n.
     */
    public synchronized void inc(){
        n++;
    }

    /**
     * Método que lee el valor de la variable n.
     */
    public synchronized long observer(){
        return n;
    }



}
