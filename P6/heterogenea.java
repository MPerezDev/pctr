/**
 * Ejercicio 3 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

public class heterogenea implements Runnable{
    /**
     * Entero n que vamos a proteger
     */
    public static int n = 0;
    /**
     * Entero m que no vamos a proteger
     */
    public static int m = 0;
    /**
     * Objeto de la clase Object que usaremos como cerrojo
     */
    public static Object cerrojo = new Object();

    /**
     * Constructor de la clase usaheterogenea
     */
    heterogenea(){}

    /**
     * Método modificaM que incrementa el valor de m.
     */
    void modificaM(){
        m += 1;
    }

    /**
     * Método modificaN que incrementa el valor de n.
     */
    void modificaN(){
        synchronized(cerrojo){
            n += 1;
        }
    }

    /**
     * Método run de la clase usaheterogenea. Llama a los métodos modificaM y modificaN.
     */
    public void run(){
        for(int i = 0; i < 100000; i++){
            modificaM();
            modificaN();
        }
    }

}
