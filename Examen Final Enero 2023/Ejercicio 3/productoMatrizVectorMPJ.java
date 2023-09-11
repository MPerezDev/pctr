/**
 * Examen Enero 2023 - Ejercicio 3
 * @author Manuel Pérez Ruiz
 */

import mpi.*;

//Clase en la que haremos el producto de una matriz por un vector usando mpj con tres procesos
public class productoMatrizVectorMPJ {

    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int unitSize = 2;
        int n = 3;
        int[] vector = new int[n];
        int[] matriz = new int[n*n];
        int[] recvbufer = new int[n];
        int[] resultado = new int[n];

        //Inicializamos la matriz y el vector en el proceso emisor (usamos BCast para mandar vector y Scatter para mandar cada fila de la matriz)  
        if (rank == emisor) {
            for (int i = 0; i < n*n; i++) {
                matriz[i] = i*2;
            }

            for (int i = 0; i < n; i++) {
                vector[i] = 1;
            }
            
            for (int i = 0; i < n; i++) {
                resultado[i] = matriz[i]*vector[i];
            }

            //Multiplicamos el vector resultado por el vector y lo guardamos en el mismo vector
            for (int i = 0; i < n; i++) {
                resultado[i] = resultado[i] * vector[i];
            }
            
        }

        //Mandamos el vector con BCast
        MPI.COMM_WORLD.Bcast(vector, 0, n, MPI.INT, emisor);
        MPI.COMM_WORLD.Scatter(matriz, 0, n, MPI.INT, recvbufer, 0, n, MPI.INT, emisor);


        if(rank != emisor){

            //Declaramos una variable donde vamos a calcular el resultado
            int[] aux = new int[n];

            //Calculamos el producto de la fila de la matriz por el vector
            for (int i = 0; i < n; i++) {
                aux[i] = recvbufer[i] * vector[i];
            }
            //Enviamos el resultado al proceso emisor
            MPI.COMM_WORLD.Send(aux, 0, n, MPI.INT, emisor, 0);


        }

        if(rank == emisor){
            //Recibimos los resultados de los otros procesos y los sumamos al vector resultado
            int[] aux2 = new int[n];
            for (int i = 1; i < n; i++) {
                MPI.COMM_WORLD.Recv(aux2, 0, n, MPI.INT, i, 0);
                for (int j = 0; j < n; j++) {
                    resultado[j] = resultado[j] + aux2[j];
                }
            }

            //Imprimimos el resultado
            System.out.println("Vector producto resultante: [" + resultado[0] + ", " + resultado[1] + ", " + resultado[2] + "]");
            

        }

        

        MPI.Finalize();


    }

    
}
