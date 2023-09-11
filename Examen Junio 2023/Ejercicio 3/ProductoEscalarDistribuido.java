/**
 * Examen Junio 2023 - Ejercicio 3
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */

import mpi.*;

public class ProductoEscalarDistribuido {
    
    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int vectorA[] = {1,1,5,5,1,7,0,2,7};
        int vectorB[] = {0,8,4,2,9,2,6,4,3};
        int aux[] = new int[9];
        int recvbufer[] = new int[3];
        int recvbufer2[] = new int[3];

        int unitSize = 3;

        if(rank == emisor){
            System.out.println("Vector A: [" + vectorA[0] + ", " + vectorA[1] + ", " + vectorA[2] + ", " + vectorA[3] + ", " + vectorA[4] + ", " + vectorA[5] + ", " + vectorA[6] + ", " + vectorA[7] + ", " + vectorA[8] + "]");
            
            System.out.println("Vector B: [" + vectorB[0] + ", " + vectorB[1] + ", " + vectorB[2] + ", " + vectorB[3] + ", " + vectorB[4] + ", " + vectorB[5] + ", " + vectorB[6] + ", " + vectorB[7] + ", " + vectorB[8] + "]");
            
        }

        MPI.COMM_WORLD.Scatter(vectorA, 0, unitSize, MPI.INT, recvbufer, 0, unitSize, MPI.INT, emisor);
        MPI.COMM_WORLD.Scatter(vectorB, 0, unitSize, MPI.INT, recvbufer2, 0, unitSize, MPI.INT, emisor);
        
        

        //Calculamos el producto escalar de los vectores, teniendo en cuenta que el proceso raíz también calcula 3 posiciones
        System.out.println("Proceso: " + rank + "Vector: [" + recvbufer[0] + ", " + recvbufer[1] + ", " + recvbufer[2] + "]");
        System.out.println("Proceso: " + rank + "Vector: [" + recvbufer2[0] + ", " + recvbufer2[1] + ", " + recvbufer2[2] + "]");
        for(int i=0; i<unitSize; i++){ 
            aux[rank * unitSize + i] = recvbufer[i] * recvbufer2[i];
            
        }

        MPI.COMM_WORLD.Gather(aux, 0, unitSize, MPI.INT, aux, 0, unitSize, MPI.INT, emisor);

        if(rank==emisor){
            //Suma todos los elementos resultantes de la multiplicación de los vectores y los muestra por pantalla
            int resultado = 0;
            for(int i=0; i<9; i++){
                resultado += aux[i];
            }

            System.out.println("Producto escalar distribuido: " + resultado);
        }


        

        MPI.Finalize();
    }


}
