/**
 * Ejercicio 2 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

 /**
  * Clase barrera que usa la clase CyclicBarrier para citar 3 hebras en una barrera.
  */
public class barrera extends Thread{
    
    /**
     * Atributo que representa la barrera.
     */
    private CyclicBarrier barrera;
    
    /**
     * Constructor de la clase barrera.
     */
    public barrera(CyclicBarrier b){
        barrera = b;
    }

    /**
     * Método que ejecuta las hebras.
     */
    public void run(){
        try{
            System.out.println("Hebra " + Thread.currentThread().getName() + " llega a la barrera.");
            barrera.await();
            System.out.println("Hebra " + Thread.currentThread().getName() + " pasa la barrera.");
        }catch(InterruptedException e){
            System.out.println("Hebra " + Thread.currentThread().getName() + " interrumpida.");
        }catch(BrokenBarrierException e){
            System.out.println("Hebra " + Thread.currentThread().getName() + " interrumpida.");
        }
    }

    /**
     * Método main que crea las hebras.
     */
    public static void main(String[] args){
        CyclicBarrier barrera = new CyclicBarrier(3);
        barrera h1 = new barrera(barrera);
        barrera h2 = new barrera(barrera);
        barrera h3 = new barrera(barrera);
        h1.start();
        h2.start();
        h3.start();
    }

}
