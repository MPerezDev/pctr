
/**
 * @author Manuel Pérez Ruiz
 */

public class prodEscalarParalelo extends Thread {
    
    private static int tamanno = 1000000;
    private static int[] vector1 = new int[tamanno];
    private static int[] vector2 = new int[tamanno];
    private int inicio;
    private int fin;
    private int idHebra;
    private static int[] resultado;


    /**
     * Método constructor de la clase prodEscalarParalelo.
     * 
     */
    public prodEscalarParalelo(int idHebra, int inicio, int fin){

        this.idHebra = idHebra;
        this.inicio = inicio;
        this.fin = fin;

    }


    /**
     * Método que escala el vector.
     */
    public void run(){
        for(int i = inicio; i < fin; i++){
            resultado[idHebra] = resultado[idHebra] + (vector1[i] * vector2[i]);
        }
    }

    

     public static void main(String[] args) {

        long resultadoFinal = 0;

        // Inicializamos los vectores
        for (int i = 0; i < tamanno; i++) {
            vector1[i] = 1;
            vector2[i] = i+1;
        }

        //Inicializamos el vector resultado
        for (int i = 2; i <= 10; i+=2) {

            resultado = new int[i];
            for(int j = 0; j < resultado.length; j++){
                resultado[j] = 0;
            }

            // Creamos las hebras y las iniciamos

            Thread hebras [] = new Thread[i];

            long startTime = System.nanoTime(); //Comenzamos a contar el tiempo

            for(int j = 0; j < i; j++){
                hebras[j] = new prodEscalarParalelo(j, (j*tamanno)/i, ((j+1)*tamanno)/i); 
                hebras[j].start();
            }

            // Esperamos a que terminen las hebras
            for(int j = 0; j < i; j++){
                try {
                    hebras[j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Sumamos cada posición del vector resultado
            for (int j = 0; j < resultado.length; j++) {
                resultadoFinal = resultadoFinal + resultado[j];
            }

            long endTime = System.nanoTime(); //Terminamos de contar el tiempo

            // Mostramos el resultado
            System.out.println("El producto escalar de los vectores con " + i + " hilos es: " + resultadoFinal);
            System.out.println("Tiempo total: " + (endTime-startTime)/1e9 + "s");

            // Reiniciamos el resultado
            resultadoFinal = 0;


        }

        
        

     }

    






}
