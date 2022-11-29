import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 3 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

public class usaheterogenea{

    /**
     * Método main de la clase usaheterogenea.
     * @param args
     */
    public static void main(String[] args) {

        try{
        ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        for(int i = 0; i < 10; i++){
            pool.execute(new heterogenea());
        }
        pool.shutdown();
        while(!pool.isTerminated()){}
        }catch (Exception e) {System.out.println("Error...");}

        System.out.println("m = " + heterogenea.m);
        System.out.println("n = " + heterogenea.n);

    }

}
