/**
 * Ejercicio 2 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase prodMatricesSecuencial que calcula el producto de dos matrices
 */
 class prodMatricesSecuencial implements Runnable{

    /**
     * Atributos de la clase prodMatricesSecuencial
     * @param M tamaño de la matriz
     * @param A matriz A
     * @param B matriz B
     * @param C matriz C
     */
    private static int M = 2000;
    private static int A[][] = new int[M][M];
    private static int B[][] = new int[M][M];
    private static int C[][] = new int[M][M];

    /**
     * Constructor de la clase prodMatricesSecuencial
     */
    prodMatricesSecuencial(){}

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
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < M; k++){
                    C[i][j] = C[i][j] + (A[i][k] * B[k][j]);
                }
            }
        }
    }

    /**
     * Método main de la clase prodMatricesSecuencial
     * @param args
     */
    public static void main(String[] args) throws Exception{

        inicializarMatriz();

        long tInicio = System.nanoTime();
        prodMatricesSecuencial pms = new prodMatricesSecuencial();
        pms.run();
        long tFin = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (tFin - tInicio)/1e9 + " segundos");


    }


 }