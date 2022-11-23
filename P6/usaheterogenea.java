import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 3 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

public class usaheterogenea implements Runnable{
    /**
     * Entero n que vamos a proteger
     */
    public static int n;
    /**
     * Entero m que no vamos a proteger
     */
    public static int m;
    /**
     * Objeto de la clase Object que usaremos como cerrojo
     */
    public static Object cerrojo = new Object();

    /**
     * Constructor de la clase usaheterogenea
     */
    usaheterogenea(){}

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

    /**
     * Método main de la clase usaheterogenea.
     * @param args
     */
    public static void main(String[] args) {

        n = 0;
        m = 0;
        try{
        ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for(int i = 0; i < 10; i++){
            pool.execute(new usaheterogenea());
        }
        pool.shutdown();
        while(!pool.isTerminated()){}
        }catch (Exception e) {System.out.println("Error...");}

        System.out.println("m = " + m);
        System.out.println("n = " + n);

    }

}
