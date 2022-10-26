
/**
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase Usa_tareaRunnable que crea dos objetos de la clase tareaRunnable,
  * las convierte a hilos y los ejecuta.
  */

public class Usa_tareaRunnable {
    
    /**
     * Método main de la clase Usa_tareaRunnable.
     * @param args
     * @param nVueltas número de vueltas que dará cada hilo.
    */
    public static void main(String[] args){
        int nVueltas = 10000000;
        tareaRunnable h1 = new tareaRunnable(0, nVueltas);
        tareaRunnable h2 = new tareaRunnable(1, nVueltas);
        Thread r1 = new Thread(h1);
        Thread r2 = new Thread(h2);
        r1.start();
        r2.start();
        try{
            r1.join();
            r2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("El valor de n es: " + tareaRunnable.n);
    }

}
