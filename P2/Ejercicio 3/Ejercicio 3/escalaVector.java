/**
 * @author Manuel Pérez Ruiz
 * Clase escalaVector que escala un vector de enteros.
 */

public class escalaVector{

    /**
     * @param vector vector de enteros.
     * @param escala factor de escala por la que se multiplicará el vector.
     * @param tamanno tamaño del vector.
     */
    private int[] vector;
    private int escala;
    private static int tamanno = 1000000;

    /**
     * Método constructor de la clase escalaVector.
     * 
     */
    public escalaVector(int[] vector, int escala){
        this.vector = vector;
        this.escala = escala;
    }
    
    /**
     * Método que escala el vector.
     */

    public void run(){
        for(int i = 0; i < vector.length; i++){
            vector[i] = vector[i] * escala;
        }
    }

    /**
     * Método main de la clase escalaVector.
     * @param args
     * @param vector vector de enteros.
     */

    public static void main(String[] args) {
        int[] vector = new int[tamanno];
        for(int i = 0; i < vector.length; i++){
            vector[i] = (int)(Math.random()*100);
        }
        escalaVector escalaVector = new escalaVector(vector, 10);
        escalaVector.run();
        
    }



}