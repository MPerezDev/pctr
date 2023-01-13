/**
 * Ejercicio 1 - Práctica 10
 * @author Manuel Pérez Ruiz
 */

 import mpi.*;

/**
 * Clase que calcula el producto interno de dos vectores de enteros usando MPJ-express
 */
public class prodInterno {

    /**
     * Método main de la clase prodInterno, en la que iniciamos MPI y preparamos las órdenes para
     * el proceso emisor y el proceso receptor.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int emisor = 0; int receptor = 1;
        int tag = 100; int unitSize = 4;
        
        if( rank == emisor ){
            int bufer[] = new int[4];
            int bufer2[] = new int[4];
            for(int i=0; i<bufer.length; i++){
                bufer[i] = i+1;
                bufer2[i] = i+1;
            }
            MPI.COMM_WORLD.Send(bufer, 0, unitSize, MPI.INT, receptor, tag);
            MPI.COMM_WORLD.Send(bufer2, 0, unitSize, MPI.INT, receptor, tag);
        } else {
            int revbufer[] = new int[4];
            int revbufer2[] = new int[4];
            MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.INT, emisor, tag);
            MPI.COMM_WORLD.Recv(revbufer2, 0, unitSize, MPI.INT, emisor, tag);
            int prodInterno = 0;
            for(int i=0; i<revbufer.length; i++){
                prodInterno += revbufer[i] * revbufer2[i];
            }
            System.out.println("El producto interno es: "+prodInterno);
        }
        
        MPI.Finalize();

    }


}
