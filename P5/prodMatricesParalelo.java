/**
 * Ejercicio 2 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

//Producto de matrices paralelo usando la ecuación de Subramanian
class prodMatricesParalelo implements Runnable{

    private static int M = 10000;
    private static int A[][] = new int[M][M];
    private static int B[][] = new int[M][M];
    private static int C[][] = new int[M][M];
    private int inicio;
    private int fin;


    prodMatricesParalelo(int inicio, int fin){
        this.inicio = inicio;
        this.fin = fin;
    }

    public void inicializarMatriz(int matriz[][]){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                matriz[i][j] = (int)(Math.random()*10);
            }
        }
    }

    public void run(){
        for(int i = inicio; i < fin; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < M; k++){
                    C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        int numTareas = Runtime.getRuntime().availableProcessors();
        int tamanno = M/numTareas;
        int inicio = 0;
        int fin = tamanno;

    }

}