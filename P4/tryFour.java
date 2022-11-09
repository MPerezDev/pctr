/**
 * Ejercicio 1 - Práctica 4
 * @author Manuel Pérez Ruiz
 */


 /**
  * Clase tryFour que representa la cuarta etapa del refinamiento sucesivo.
  */

public class tryFour extends Thread{
  
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
   * Constructor de la clase tryFour
   */
  public tryFour(int tipoHilo) {this.tipoHilo=tipoHilo;}

  /**
   * Método run de la clase tryFour
   */

  public void run(){
    switch(tipoHilo){
      case 1:{for(int i=0; i<nVueltas; i++){
              C1 = true;
              while(C2){
                  C1 = false;
                  C1 = true;	
              }
                    
            n++;
            System.out.println(getName());
            C1 = false;
              
            }
            break;}
      case 2: {for(int i=0; i<nVueltas; i++){
              C2 = true;
              while(C1){
                  C2 = false;
                  C2 = true;
              } 
              
            n--;
            System.out.println(getName());
            C2 = false;
              }
            }break;
    }
  }

  /**
   * Método main de la clase tryFour
   * @param args
   * @param h1 hilo 1
   * @param h2 hilo 2
   */
  public static void main(String[] args) throws InterruptedException{
    tryFour h1 = new tryFour(1);
    tryFour h2 = new tryFour(2);
    h1.start(); 
    h2.start();
    h1.join(); 
    h2.join();
    System.out.println(n);
  }

}
