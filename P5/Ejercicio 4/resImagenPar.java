/**
 * Ejercicio 4 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Clase resImagenPar que escala una imagen.
 */

public class resImagenPar implements Runnable{

    /**
     * Atributos de la clase resImagenPar
     * @param k tamaño de la imagen
     * @param matriz matriz de la imagen
     * @param matriz2 matriz auxiliar
     * @param inicio inicio del rango
     * @param fin fin del rango
     */
    private static int k = 10000;
    private static int matriz [][] = new int [k][k];
    private static int matriz2 [][] = new int [k][k];
    private int inicio;
    private int fin;

    /**
     * Método que rellena la matriz con valores aleatorios
     */
    public static void valorMatriz(){
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                matriz[i][j] = (int) (Math.random() * 256);
            }
        }
    }

    /**
     * Constructor de la clase resImagenPar
     * @param inicio
     * @param fin
     */
    public resImagenPar(int inicio, int fin){
        this.inicio = inicio;
        this.fin = fin;
    }

    /**
     * Método que escala la imagen
     */
    public void run(){
        for(int i = inicio; i < fin; i++){
            for(int j = 0; j < k; j++){
                matriz2[i][j] = 4*matriz[i][j];
                if(i != 0){
                    matriz2[i][j] -= matriz[i-1][j];
                }
                if(j != 0){
                    matriz2[i][j] -= matriz[i][j-1];
                }
                if(i != k-1){
                    matriz2[i][j] -= matriz[i+1][j];
                }
                if(j != k-1){
                    matriz2[i][j] -= matriz[i][j+1];
                }

                matriz2[i][j] /= 8;

            }

        }
    }

    /**
     * Método main de la clase resImagenPar
     * @param args
     */
    public static void main(String[] args) {

        valorMatriz();
        int nCores = Runtime.getRuntime().availableProcessors();
        int cB = 0;
        int nTareas = nCores/(1 - cB);
        int aux = k/nTareas;

        long tInicio = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(nTareas);

        for(int i = 0; i < nTareas; i++){
            executor.execute(new resImagenPar(i*aux, (i+1)*aux));
        }
        executor.shutdown();
        while(!executor.isTerminated());

        long tFin = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (tFin - tInicio)/1e9 + " segundos");


    }

    
}
