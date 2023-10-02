
//Esta clase se va a usar de prueba para mostrar las utilidades de copilot. Este código tendrá una función run que incremente una variable bajo exclusión mutua usando un semaforo binario, 
//y en el main usaremos dos hilos que incrementen en 1000 una variable global.

import java.util.concurrent.Semaphore;

public class prueba {
    
    public static int variable = 0;
    public static Semaphore semaforo = new Semaphore(1);
    
    public void run(){
        try{
            semaforo.acquire();
            variable++;
            semaforo.release();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread h1 = new Thread();
        Thread h2 = new Thread();
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(variable);
    }


}
