/**
 * Ejercicio 4 - Práctica 5
 * @author Manuel Pérez Ruiz
 */


 /**
  * Clase resImagen que escala una imagen.
  */
public class resImagen {

    /**
     * Atributos de la clase resImagen
     * @param k tamaño de la imagen
     * @param matriz matriz de la imagen
     */
    private static int k = 10000;
    private static int matriz [][] = new int [k][k];

    /**
     * Constructor de la clase resImagen
     */
    public resImagen() {}

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
     * Método que escala la imagen
     */
    public void run(){
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                matriz[i][j] = 4*matriz[i][j];
                if(i != 0){
                    matriz[i][j] -= matriz[i-1][j];
                }
                if(j != 0){
                    matriz[i][j] -= matriz[i][j-1];
                }
                if(i != k-1){
                    matriz[i][j] -= matriz[i+1][j];
                }
                if(j != k-1){
                    matriz[i][j] -= matriz[i][j+1];
                }

                matriz[i][j] /= 8;

            }

        }
    }

    /**
     * Método main de la clase resImagen
     * @param args
     */
    public static void main(String[] args) {
        
        valorMatriz();
        long tInicio = System.nanoTime();
        resImagen res = new resImagen();
        res.run();
        long tFin = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (tFin - tInicio)/1e9 + "s");
    }

}
