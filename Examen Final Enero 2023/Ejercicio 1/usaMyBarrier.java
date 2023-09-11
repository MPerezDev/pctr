/**
 * Ejercicio 1 - Examen Final Enero 2023
 * @author Manuel Pérez Ruiz
 */

public class usaMyBarrier extends Thread{

    private myBarrier barrera;

    usaMyBarrier(myBarrier bar) { 

        barrera = bar; 

    }

    public void run() {

        System.out.println(this.getName()+" llegando a la barrera...");

        barrera.toWaitOnBarrier();

        System.out.println(this.getName()+" saliendo de la barrera...");

    }

    public static void main(String[] args) throws Exception {

        myBarrier barrera = new myBarrier(3);

        usaMyBarrier h1 = new usaMyBarrier(barrera);
        usaMyBarrier h2 = new usaMyBarrier(barrera);
        usaMyBarrier h3 = new usaMyBarrier(barrera);
        usaMyBarrier h4 = new usaMyBarrier(barrera);
        usaMyBarrier h5 = new usaMyBarrier(barrera);
        usaMyBarrier h6 = new usaMyBarrier(barrera);

        //Lanzamos las tres primeras
        System.out.println("main creando para tres hebras...");
        h1.start(); 
        h2.start(); 
        h3.start();
        h1.join();
        h2.join();
        h3.join();

        //Reseteamos la barrera
        barrera.resetBarrier();

        System.out.println("main reseteando barrera para tres nuevas hebras...");

        //Lanzamos las tres siguientes
        h4.start(); 
        h5.start(); 
        h6.start();
        h4.join();
        h5.join();
        h6.join();

        System.out.println("main terminando...");

    }



    
}
