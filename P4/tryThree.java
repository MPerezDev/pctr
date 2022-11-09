
/**
 * Práctica 4 - Ejercicio 1
 * @author Manuel Pérez Ruiz
 * 
 */

 //Tercer intento del refinamiento sucesivo según Ben-Ari
public class tryThree extends Thread{

    private int tipoHilo;
	private static volatile int nVueltas = 10000;
	private static volatile int n = 0;
	private static volatile boolean C1 = false;
	private static volatile boolean C2 = false;

    public tryThree(int tipoHilo) {this.tipoHilo=tipoHilo;}

    public void run(){
      switch(tipoHilo){
        case 1:{for(int i=0; i<nVueltas; i++){
                C1 = true;
                while(C2);	      
        	    n++;
              System.out.println(getName());
        	    C1 = false;
        	      
        	    }
        	    break;}
        case 2: {for(int i=0; i<nVueltas;i++){
                C2 = true;
                while(C1);  
        	    n--;
              System.out.println(getName());
        	    C2 = false;
                }
        	    }break;
      }
    }

    public static void main(String[] args) throws InterruptedException{
      tryThree h1 = new tryThree(1);
      tryThree h2 = new tryThree(2);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(n);
    }

}
