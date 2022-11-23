import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 2 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase arrSeguro que escribe datos de un objeto de array de forma segura usando cerrojos synchronized.
 */
public class arrSeguro extends Thread{
    
    /**
     * Array de enteros donde vamos a guardar la suma de las "puntuaciones" de cada hilo
     */
    public static int[] array = new int[200];

    /**
     * Objeto de la clase Object que usaremos como cerrojo
     */
    public static Object cerrojo = new Object();

    /**
     * Constructor de la clase arrSeguro
     */
    arrSeguro(){}


    /**
     * Método run de la clase arrSeguro. Escribe los datos del array de forma segura.
     */
    public void run(){

        for(int i = 0; i < 200; i++){
            synchronized(cerrojo){
                array[i] += (int)(Math.random()*20 + 1);
            }
        }
    }

    /**
     * Método main de la clase arrSeguro.
     * @param args
     */
    public static void main (String[] args){
        
        for(int i = 0; i < 200; i++){
            array[i] = 0;
        }

        try{
            ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            for(int i = 0; i < 10; i++){
                pool.execute(new arrSeguro());
            }
            pool.shutdown();
            while(!pool.isTerminated()){}

        }catch (Exception e) {System.out.println("Error...");}

    }

}


