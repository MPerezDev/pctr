
/**
 * @author Manuel Pérez Ruiz
 *
 */

 /*
  * Clase Usa_hebra que crea dos hebras y las ejecuta.
  */

public class Usa_hebra {
    
    public static void main(String[] args) {
        int nVueltas = 10000000;
        hebra h1 = new hebra(0, nVueltas);
        hebra h2 = new hebra(1, nVueltas);
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("El valor de n es: " + hebra.n);
    }
    

}
