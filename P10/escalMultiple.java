/**
 * Ejercicio 2 - Práctica 10
 * @author Manuel Pérez Ruiz
 */

 import mpi.*;


/**
 * Clase que escala un vector de enteros en cada proceso slave usando MPJ-express.
 */
public class escalMultiple {
    
    /**
     * Método main de la clase escalMultiple, en la que iniciamos MPI y preparamos las órdenes para el proceso master y los procesos slave.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int unitSize = 10;
        int v[] = new int[unitSize];

        if( rank == emisor ){
            for(int i=0; i<v.length; i++){
                v[i] = i+1;
            }
        }

        MPI.COMM_WORLD.Bcast(v, 0, unitSize, MPI.INT, emisor);

        if( rank != emisor ){
            for(int i=0; i<v.length; i++){
                v[i] = v[i] * rank;
            }
            System.out.println("Proceso "+rank+" - Vector escalado: {" + v[0] + ", " + v[1] + ", " + v[2] + ", " + v[3] + ", " + v[4] + ", " + v[5] + ", " + v[6] + ", " + v[7] + ", " + v[8] + ", " + v[9] + "}");
            
        }

        MPI.Finalize();

    }


}
