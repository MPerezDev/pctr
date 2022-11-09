/**
 * Ejercicio 2- Práctica 4
 * @author Manuel Pérez Ruiz
 */

public class algDekker extends Thread{
    private int tipoHilo;
    private static volatile int n = 0;
    private static volatile boolean C1 = false;
    private static volatile boolean C2 = false;
    private static volatile int turno = 1;

    public algDekker(int tipoHilo) {this.tipoHilo=tipoHilo;}

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
