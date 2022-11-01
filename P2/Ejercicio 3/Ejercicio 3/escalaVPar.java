
/**
 * @author Manuel Pérez Ruiz
 * Clase escalaVPar que escala un vector de enteros de forma paralela.
 */

public class escalaVPar{

    /**
     * @param tamanno tamaño máximo del vector.
     */

    public static int tamanno = 1000000;

    /**
     * Método main de la clase escalaVPar.
     * @param args
     * @param vector vector de enteros.
     */
    public static void main(String[] args) {
        int[] vector = new int[tamanno];
        for(int i = 0; i < vector.length; i++){
            vector[i] = (int)(Math.random()*100);
        }
        hebra hebra1 = new hebra(vector, 15, 0, escalaVPar.tamanno/4);
        hebra hebra2 = new hebra(vector, 15, escalaVPar.tamanno/4, escalaVPar.tamanno/2);
        hebra hebra3 = new hebra(vector, 15, escalaVPar.tamanno/2, 3*escalaVPar.tamanno/4);
        hebra hebra4 = new hebra(vector, 15, 3*escalaVPar.tamanno/4, escalaVPar.tamanno);
        hebra1.start();
        hebra2.start();
        hebra3.start();
        hebra4.start();
        try{
            hebra1.join();
            hebra2.join();
            hebra3.join();
            hebra4.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }


}

/**
 * Clase hebra que escala un vector de enteros.
 */

class hebra extends Thread{

    /**
     * @param vector vector de enteros.
     * @param escala factor de escala por la que se multiplicará el vector.
     * @param inicio posición inicial del vector.
     * @param fin posición final del vector.
     */

    private int[] vector;
    private int escala;
    private int inicio;
    private int fin;

    /**
     * Método constructor de la clase hebra.
     */

    public hebra(int[] vector, int escala, int inicio, int fin){
        this.vector = vector;
        this.escala = escala;
        this.inicio = inicio;
        this.fin = fin;
    }

    /**
     * Método que escala el vector.
     */

    public void run(){
        for(int i = inicio; i < fin; i++){
            vector[i] = vector[i] * escala;
        }
    }
}