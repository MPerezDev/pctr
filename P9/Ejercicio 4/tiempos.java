import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ejercicio 4 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase tiempos con la que vamos a conocer la rapidez temporal de las técnicas de exclusión mutua.
 */
public class tiempos {
    
    /**
     * Atributos que usamos para calcular el tiempo de ejecución.
     */
    private long tiempoInicial, tiempoTotal;

    /**
     * Atributos que incrementamos en la sección crítica.
     */
    private int n = 0;

    /**
     * Atributo que nos indica el tipo de cerrojo que vamos a usar.
     */
    public int tipo;

    /**
     * Constructor de la clase tiempos.
     * @param tipo Tipo de cerrojo que vamos a usar.
     */
    public tiempos(int tipo){
        this.tipo = tipo;
    }

    /**
     * Método que incrementa el atributo n en la sección crítica.
     * @param iter Número de iteraciones que vamos a realizar.
     * @return Devuelve el tiempo de ejecución.
     */
    public long f(long iter){

        switch(tipo){
            //Cerrojos synchronized
            case 0:
                tiempoInicial = System.nanoTime();
                for(int i = 0; i < iter; i++){
                    synchronized(this){
                        n++;
                    }
                }
                tiempoTotal = System.nanoTime() - tiempoInicial;

            break;

            //Cerrojos ReentrantLock
            case 1:
                ReentrantLock cerrojo = new ReentrantLock();
                tiempoInicial = System.nanoTime();
                for(int i = 0; i < iter; i++){
                    cerrojo.lock();
                    try{
                        n++;
                    }finally{
                        cerrojo.unlock();
                    }
                }
                tiempoTotal = System.nanoTime() - tiempoInicial;

            break;

            //Cerrojos Semaphore
            case 2:
                Semaphore semaforo = new Semaphore(1);
                tiempoInicial = System.nanoTime();
                for(int i = 0; i < iter; i++){
                    try{
                        semaforo.acquire();
                        n++;
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        semaforo.release();
                    }
                }
                tiempoTotal = System.nanoTime() - tiempoInicial;

            break;

            //Con objetos atomic
            case 3:
                AtomicLong atomic = new AtomicLong(0);

                tiempoInicial = System.nanoTime();
                for(int i = 0; i < iter; i++){
                    atomic.incrementAndGet();
                }
                tiempoTotal = System.nanoTime() - tiempoInicial;

            break;

        }

        return tiempoTotal;

    }

    /**
     * Método main que crea los objetos de la clase tiempos y los ejecuta.
     * @param args
     */
    public static void main(String[] args) {

        long iter = Long.parseLong(args[0]);

        tiempos t1 = new tiempos(0);
        tiempos t2 = new tiempos(1);
        tiempos t3 = new tiempos(2);
        tiempos t4 = new tiempos(3);

        System.out.println("Tiempo de ejecución con cerrojos synchronized: " + t1.f(iter)/1e6 + " milisegundos");
        System.out.println("Tiempo de ejecución con cerrojos ReentrantLock: " + t2.f(iter)/1e6 + " milisegundos");
        System.out.println("Tiempo de ejecución con cerrojos Semaphore: " + t3.f(iter)/1e6 + " milisegundos");
        System.out.println("Tiempo de ejecución con objetos Atomic: " + t4.f(iter)/1e6 + " milisegundos");

    }

}
