/**
 * 
 * @author Manuel Pérez Ruiz
 * Clase concursoLambda que crea dos hebras que incrementan y decrementan nVueltas veces el valor de aux.
*/

public class concursoLambda {
    
    /**
     * Atributos de la clase concursoLambda.
     * @param aux Valor auxiliar.
     */

    public static int aux = 0;

    /**
     * Método main de la clase concursoLambda.
     * @param args
     * @param nVueltas Número de vueltas que realizarán las hebras.
     */
    public static void main(String[] args) {
        int nVueltas = 1000000;

        Runnable op1 = () -> {
            for(int i = 0; i < nVueltas; i++){
                aux++;
            }
        };

        Runnable op2 = () -> {
            for(int i = 0; i < nVueltas; i++){
                aux--;
            }
        };

        Thread h1 = new Thread(op1);
        Thread h2 = new Thread(op2);
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("El valor de n es: " + aux);
    }

}
