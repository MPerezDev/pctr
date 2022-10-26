
public class escalaVector{

    private int[] vector;
    private int escala;
    private static int tamanno = 1000000;

    public escalaVector(int[] vector, int escala){
        this.vector = vector;
        this.escala = escala;
    }	

    public void run(){
        for(int i = 0; i < vector.length; i++){
            vector[i] = vector[i] * escala;
        }
    }

    public static void main(String[] args) {
        int[] vector = new int[tamanno];
        for(int i = 0; i < vector.length; i++){
            vector[i] = (int)(Math.random()*100);
        }
        escalaVector escalaVector = new escalaVector(vector, 5);
        escalaVector.run();
        
    }



}