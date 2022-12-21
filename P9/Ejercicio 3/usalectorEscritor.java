/**
 * Ejercicio 3 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase usalectorEscritor que utiliza el monitor para el problema del lector-escritor.
  */
public class usalectorEscritor implements Runnable{

    /**
     * Variable que almacena el tipo de hilo que se va a ejecutar.
     */
    private int tipoHilo;

    /**
     * Variable que almacena un objeto de la clase recurso.
     */
    private static recurso r = new recurso();

    /**
     * Variable que almacena un objeto de la clase lectorEscritor.
     */
    private static lectorEscritor le = new lectorEscritor();

    /**
     * Constructor de la clase.
     * @param tipo tipo de hilo que se va a ejecutar.
     */
    public usalectorEscritor(int tipo) {
        tipoHilo = tipo;
    }

    /**
     * Método run de la clase usalectorEscritor.
     */
    public void run() {
        switch(tipoHilo){
            //Lector
            case 0:
                for(long i = 0; i < 1000000; i++){
                    try {
                        le.iniciaLectura();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    r.observer();
                    try {
                        le.acabarLectura();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            break;
            //Escritor
            case 1:
                for(long i = 0; i < 1000000; i++){
                    try {
                        le.iniciarEscritura();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    r.inc();
                    try {
                        le.acabarEscritura();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            break;

        }
    }

    /**
     * Método main de la clase usalectorEscritor.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        Thread[] hilos = new Thread[10];
        for(int i = 0; i < 6; i++){
            if(i % 2 == 0){
                hilos[i] = new Thread(new usalectorEscritor(0));
            }else{
                hilos[i] = new Thread(new usalectorEscritor(1));
            }
        }

        for(int i = 0; i < 6; i++){
            hilos[i].start();
        }

        for(int i = 0; i < 6; i++){
            hilos[i].join();
        }

    }

    
}
