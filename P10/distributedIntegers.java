/**
 * Ejercicio 3 - Práctica 10
 * @author Manuel Pérez Ruiz
 */

import mpi.*;

/**
 * Clase distributedIntegers que calcula el número de primos en un rango determinado, distribuido por partes mediante el uso de MPJ-Express 
 */
public class distributedIntegers {
    
    /**
     * Método main de la clase distributedIntegers, en la que en la que iniciamos MPI y preparamos las órdenes para el proceso master y los procesos slave.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int v[] = new int[1];
        int vPrimo[] = new int[1];

        vPrimo[0] = 0;

        if( rank == emisor ){
            v[0] = 10000000;
        }

        MPI.COMM_WORLD.Bcast(v, 0, 1, MPI.INT, emisor);
        
        if( rank != emisor ){
            int contador = 0;
            for(int i = (rank-1)*v[0]/(size-1) + 1; i < rank*v[0]/(size-1); i++){
                if(esPrimo(i)){
                    contador++;
                }
            }   
            vPrimo[0] = contador;
        }

        MPI.COMM_WORLD.Reduce(vPrimo, 0, v, 0, 1, MPI.INT, MPI.SUM, emisor);

        if( rank == emisor ){
            System.out.println("Hay " + v[0] + " numeros primos en el rango [0, 10^7]");
        }

        MPI.Finalize();



    }

    public static boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    

}
