/**
 * Ejercicio 1 - Práctica 7
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase usprodCon que utiliza el monitor para el problema del productor-consumidor.
  */
public class usaprodCon implements Runnable {

    /**
     * Variable que almacena el tipo de hilo que se va a ejecutar.
     */
    private int tipoHilo;

    /**
     * Variable que almacena un objeto de la clase prodCon.
     */
    private static prodCon prod = new prodCon();

    /**
     * Constructor de la clase.
     * @param tipo tipo de hilo que se va a ejecutar.
     */
    public usaprodCon(int tipo) {
        tipoHilo = tipo;
    }

    /**
     * Método run de la clase usaprodCon.
     */
    public void run() {
        switch(tipoHilo){
            //Productor
            case 0:  
                try {
                    prod.producir();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            break;
            //Consumidor
            case 1:
                try {
                    prod.consumir();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            break;

        }
    }

    /**
     * Método main de la clase usaprodCon.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        Thread h1 = new Thread(new usaprodCon(0));
        Thread h2 = new Thread(new usaprodCon(1));
        Thread h3 = new Thread(new usaprodCon(0));
        Thread h4 = new Thread(new usaprodCon(1));

        h1.start();
        h2.start();
        h3.start();
        h4.start();

        h1.join();
        h2.join();
        h3.join();
        h4.join();

    }

    
}
