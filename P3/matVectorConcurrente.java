/**
 * @author Manuel Pérez Ruiz
 * Ejercicio 2 - Práctica 3
 */

public class matVectorConcurrente implements Runnable{
    
    private static int tamanno = 10000;
    private static int[][] matriz = new int[tamanno][tamanno];
    private static int[] vector = new int[tamanno];
    private static int[] resultado = new int[tamanno];
    private int inicio;
    private int fin;

    /**
     * Método constructor de la clase matVectorConcurrente.
     */
    public matVectorConcurrente(int inicio, int fin){

        this.inicio = inicio;
        this.fin = fin;

    }

    /**
     * Método que multiplica la matriz por el vector.
     */
    public void run(){
        for(int i = inicio; i < fin; i++){
            for(int j = 0; j < tamanno; j++){
                resultado[i] = resultado[i] + (matriz[i][j] * vector[j]);
            }
        }
    }

    /**
     * Método main de la clase matVectorConcurrente.
     * @param args
     * @param startTime tiempo de inicio del programa.
     * @param endTime tiempo de finalización del programa.
     * @param resultado resultado de la multiplicación de la matriz por el vector.
     * @param hebras vector de hebras.
     */
    public static void main(String[] args) throws Exception{
            
        //Inicializamos la matriz
        for(int i = 0; i < tamanno; i++){
            for(int j = 0; j < tamanno; j++){
                matriz[i][j] = (int)(Math.random()*10);
            }
        }

        //Inicializamos el vector
        for(int i = 0; i < tamanno; i++){
            vector[i] = (int)(Math.random()*10);
        }

        //Inicializamos el vector resultado
        for(int i = 0; i < tamanno; i++){
            resultado[i] = 0;
        }

        for(int i = 2; i <= 16; i+=2){
            Thread[] hebras = new Thread[i];
            long startTime = System.nanoTime();
            for(int j = 0; j < i; j++){
                hebras[j] = new Thread(new matVectorConcurrente((j*tamanno)/i, ((j+1)*tamanno)/i));
                hebras[j].start();
            }
            for(int j = 0; j < i; j++){
                try {
                    hebras[j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.nanoTime();

            System.out.println("Tiempo de ejecución con " + i + " hebras: " + (endTime-startTime)/1e6 + " milisegundos.");

            for(int j = 0; j < tamanno; j++){
                resultado[j] = 0;
            }

            Thread.sleep(2000);

        }

        //Imprimimos el resultado
        
}

}
