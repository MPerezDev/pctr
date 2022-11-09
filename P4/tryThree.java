
/**
 * Práctica 4 - Ejercicio 1
 * @author Manuel Pérez Ruiz
 * 
 */

 /**
  * Clase tryThree que representa la tercera etapa del refinamiento sucesivo.
  */
public class tryThree extends Thread{

  /**
   * @param tipoHilo variable de control. 1 para incrementar, 2 para decrementar
   * @param nVueltas número de vueltas que dará el hilo
   * @param n número que se incrementará o decrementará
   * @param C1 booleano que indica si el hilo 1 está en la sección crítica
   * @param C2 booleano que indica si el hilo 2 está en la sección crítica
   */

  private int tipoHilo;
	private static volatile int nVueltas = 10000;
	private static volatile int n = 0;
	private static volatile boolean C1 = false;
	private static volatile boolean C2 = false;

  /**
   * Constructor de la clase tryThree
   */
  public tryThree(int tipoHilo) {this.tipoHilo=tipoHilo;}

  /**
   * Método run de la clase tryThree
   */
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

  /**
   * Método main de la clase tryThree
   * @param args
   * @param h1 hilo 1
   * @param h2 hilo 2
   */
  public static void main(String[] args) throws InterruptedException{
    tryThree h1 = new tryThree(1);
    tryThree h2 = new tryThree(2);
    h1.start(); h2.start();
    h1.join(); h2.join();
    System.out.println(n);
  }

}
