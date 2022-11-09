/**
 * Ejercicio 2- Práctica 4
 * @author Manuel Pérez Ruiz
 */

/**
  * Clase algDekker que representa el algoritmo de Dekker.
  */
public class algDekker extends Thread{

    /**
     * @param tipoHilo variable de control. 1 para incrementar, 2 para decrementar
     * @param n número que se incrementará o decrementará
     * @param C1 booleano que indica si el hilo 1 está en la sección crítica
     * @param C2 booleano que indica si el hilo 2 está en la sección crítica
     * @param turno turno del hilo
     */

    private int tipoHilo;
    private static volatile int n = 0;
    private static volatile boolean C1 = false;
    private static volatile boolean C2 = false;
    private static volatile int turno = 1;

    /**
     * Constructor de la clase algDekker
     */
    public algDekker(int tipoHilo) {this.tipoHilo=tipoHilo;}

    /**
     * Método run de la clase algDekker
     */

    public void run(){
        if(tipoHilo==1){
            while(true){

                C1 = true;
                while(C2){
                    if(turno == 2){
                        C1 = false;
                        while(turno != 1);
                        C1 = true;
                    }
                }
                
                n++;
                System.out.println(getName());
                turno = 2;
                C1 = false;

            }
        }else{
            while(true){

                C2 = true;
                while(C1){
                    if(turno == 1){
                        C2 = false;
                        while(turno != 2);
                        C2 = true;
                    }
                }
                
                n--;
                System.out.println(getName());
                turno = 1;
                C2 = false;

            }

        }
    }

    /**
     * Método main de la clase algDekker
     * @param args
     * @param h1 hilo 1
     * @param h2 hilo 2
     */
    public static void main(String[] args) throws InterruptedException{
        algDekker h1 = new algDekker(1);
        algDekker h2 = new algDekker(2);
        h1.start(); 
        h2.start();
        h1.join(); 
        h2.join();
        System.out.println(n);
    }


}
