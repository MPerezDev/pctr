/**
 * Ejercicio 3 - Práctica 4
 * @author Manuel Pérez Ruiz
 */

public class algPeterson implements Runnable{
    private int tipoHilo;
    private static volatile int nVueltas = 10000;
    private static volatile int n = 0;
    private static volatile boolean C1 = false;
    private static volatile boolean C2 = false;
    private static volatile int ultimo = 1;

    public algPeterson(int tipoHilo) {this.tipoHilo=tipoHilo;}

    public void run(){
        if(tipoHilo==1){
            while(true){
                C1 = true;
                ultimo = 1;
                while(C2 && ultimo != 2);   
            }

            n++;
            System.out.println(getName());


        }
    }


}
