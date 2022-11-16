/**
 * Ejercicio 2 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

//Producto de matrices secuencial usando la ecuación de Subramanian
 class prodMatricesSecuencial implements Runnable{

    private static int M = 10000;
    private static int A[][] = new int[M][M];
    private static int B[][] = new int[M][M];
    private static int C[][] = new int[M][M];

    prodMatricesSecuencial(){}

    public void inicializarMatriz(int matriz[][]){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                matriz[i][j] = (int)(Math.random()*10);
            }
        }
    }

    public void run(){
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < M; k++){
                    C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        prodMatricesSecuencial pms = new prodMatricesSecuencial();

        pms.inicializarMatriz(A);
        pms.inicializarMatriz(B);
        pms.run();


    }


 }