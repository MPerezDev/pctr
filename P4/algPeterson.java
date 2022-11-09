/**
 * Ejercicio 3 - Práctica 4
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase algPeterson que representa el algoritmo de Peterson.
 */

public class algPeterson implements Runnable{

    /**
     * @param tipoHilo variable de control. 1 para incrementar, 2 para decrementar
     * @param n número que se incrementará o decrementará
     * @param C1 booleano que indica si el hilo 1 está en la sección crítica
     * @param C2 booleano que indica si el hilo 2 está en la sección crítica
     * @param ultimo último hilo que entró en la sección crítica
     */

    private int tipoHilo;
    private static volatile int n = 0;
    private static volatile boolean C1 = false;
    private static volatile boolean C2 = false;
    private static volatile int ultimo = 1;

    /**
     * Constructor de la clase algPeterson
     */
    public algPeterson(int tipoHilo) {this.tipoHilo=tipoHilo;}

    /**
     * Método run de la clase algPeterson
     */
    public void run(){
        if(tipoHilo==1){
            while(true){
                C1 = true;
                ultimo = 1;
                while(C2 && ultimo != 2);
                
                n++;
                System.out.println(Thread.currentThread().getName());
                C1 = false;
            }

        }else{
            while(true){
                C2 = true;
                ultimo = 2;
                while(C1 && ultimo != 1);  

                n--;
                System.out.println(Thread.currentThread().getName());
                C2 = false;
            }

        }
    }

    /**
     * Método main de la clase algPeterson
     * @param args
     * @param executor objeto de la clase ExecutorService que ejecutará los hilos
     */
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new algPeterson(1));
        executor.execute(new algPeterson(2));
        executor.shutdown();
        while(!executor.isTerminated());
        System.out.println(n);
        
    }


}
