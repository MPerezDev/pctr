import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 4 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase deadlock que crea un deadlock entre tres hebras.
  */
public class deadlock extends Thread{
    /**
     * Objeto lock1 de la clase Object que usaremos como cerrojo
     */
    private static Object lock1 = new Object();
    /**
     * Objeto lock2 de la clase Object que usaremos como cerrojo
     */
    private static Object lock2 = new Object();
    /**
     * Objeto lock3 de la clase Object que usaremos como cerrojo
     */
    private static Object lock3 = new Object();
    /**
     * Variable que usaremos para saber a qué hebra recurre
     */
    private int aux;

    /**
     * Constructor de la clase deadlock
     * @param aux Variable que usaremos para saber a qué hebra recurre
     */
    public deadlock(int aux){
        this.aux = aux;
    }

    /**
     * Método run de la clase deadlock. Crea un deadlock entre las tres hebras que hemos creado.
     */
    public void run(){
        switch(aux){
            case 0:
                synchronized(lock1){
                    synchronized(lock2){
                        synchronized(lock3){
                            System.out.println("Hilo " + this.getName() + " bloqueado con lock3");
                        }
                    }
                }
            break;

            case 1:
                synchronized(lock3){
                    synchronized(lock2){
                        synchronized(lock1){
                            System.out.println("Hilo " + this.getName() + " bloqueado con lock1");
                        }
                    }
                }
            break;

            case 2:
                synchronized(lock1){
                    synchronized(lock3){
                        synchronized(lock2){
                            System.out.println("Hilo " + this.getName() + " bloqueado con lock2");
                        }
                    }
                }
            break;

            default:
                System.out.println("Error...");
            break;
        }
    }
    
    /**
     * Método main de la clase deadlock.
     * @param args
     */
    public static void main(String[] args) {
        
        ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for(int i = 0; i < 100; i++){
            pool.execute(new deadlock((int)(Math.random()*3)));
        }
        pool.shutdown();
        while(!pool.isTerminated()){}
        
    }
    
    


}

