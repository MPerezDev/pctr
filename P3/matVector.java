import java.lang.Math;

/**
 * @author Manuel Pérez Ruiz
 * Ejercicio 2 - Práctica 3
 */

public class matVector{
    
    private static int tamanno = 10000;
    private static int[][] matriz = new int[tamanno][tamanno];
    private static int[] vector = new int[tamanno];
    private static int[] resultado = new int[tamanno];

    /**
     * Método que multiplica la matriz por el vector.
     */
    public void productoMatriz(){
        for(int i = 0; i < tamanno; i++){
            for(int j = 0; j < tamanno; j++){
                resultado[i] = resultado[i] + (matriz[i][j] * vector[j]);
            }
        }
    }

    /**
     * Método main de la clase matVector.
     * @param args
     * @param startTime tiempo de inicio del programa.
     * @param endTime tiempo de finalización del programa.
     * @param resultado resultado de la multiplicación de la matriz por el vector.
     */
    public static void main(String[] args) {
            
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
            
            long startTime = System.nanoTime();
            matVector producto = new matVector();
            producto.productoMatriz();

            long endTime = System.nanoTime();           
            
            //Imprimimos el resultado
            

            System.out.println("Tiempo de ejecución: " + (endTime - startTime)/1e6 + " milisegundos");
    }



}
