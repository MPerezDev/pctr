/**
 * @author Manuel Pérez Ruiz
 * 
 */


public class prodEscalar {

    /**
     * @param tamanno tamaño de los vectores.
     * @param resultado resultado de la multiplicación de los vectores.
     */
    private static int tamanno = 1000000;
    private static long resultado = 0;
    
    /**
     * Método main de la clase prodEscalar.
     * @param args
     * @param vector1 vector de enteros.
     * @param vector2 vector de enteros.
     */
    public static void main(String[] args) {

        int[] vector1 = new int[tamanno];
        int[] vector2 = new int[tamanno];

        for(int i = 0; i < tamanno; i++){
            vector1[i] = 1;
            vector2[i] = i+1;
        }

        long startTime = System.nanoTime(); //Comenzamos a contar el tiempo

        for(int i = 0; i < tamanno; i++){
            resultado = resultado + (vector1[i] * vector2[i]);
        }

        long endTime = System.nanoTime(); //Terminamos de contar el tiempo


        System.out.println("El resultado es: " + resultado);
        System.out.println("Tiempo total: " + (endTime-startTime)/1e9 + " s");
    }

}