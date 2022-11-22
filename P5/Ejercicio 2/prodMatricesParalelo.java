/**
 * Ejercicio 2 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Clase prodMatricesParalelo que calcula el producto de dos matrices
 */
public class prodMatricesParalelo implements Runnable{
    
    /**
     * Atributos de la clase prodMatricesParalelo
     * @param M tamaño de la matriz
     * @param A matriz A
     * @param B matriz B
     * @param C matriz C
     * @param inicio inicio del rango
     * @param fin fin del rango
     */
    private static int M = 2000;
    private static int A[][] = new int[M][M];
    private static int B[][] = new int[M][M];
    private static int C[][] = new int[M][M];
    private int inicio;
    private int fin;

    /**
     * Constructor de la clase prodMatricesParalelo
     * @param inicio
     * @param fin
     */
    prodMatricesParalelo(int inicio, int fin){
        this.inicio = inicio;
        this.fin = fin;
    }

    /**
     * Método que rellena las matrices con valores aleatorios
     */
    public static void inicializarMatriz(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                A[i][j] = (int)(Math.random()*10);
                B[i][j] = (int)(Math.random()*10);
            }
        }
    }

    /**
     * Método que calcula el producto de las matrices
     */
    public void run(){
        for(int i = inicio; i < fin; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < M; k++){
                    C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }
    }

    /**
     * Método main de la clase prodMatricesParalelo
     * @param args
     */
    public static void main(String[] args) {
        
        inicializarMatriz();

        int nCores = Runtime.getRuntime().availableProcessors();
        int cB = 0;
        int nTareas = nCores/(1 - cB);
        int aux = M/nTareas;

        long tInicio = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(nTareas);

        for(int i = 0; i < nTareas; i++){
            executor.execute(new prodMatricesParalelo(i*aux, (i+1)*aux));
        }
        executor.shutdown();
        while(!executor.isTerminated());

        long tFin = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (tFin - tInicio)/1e9 + " segundos");

    }



}
