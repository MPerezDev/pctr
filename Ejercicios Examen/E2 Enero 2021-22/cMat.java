//COMPILACION: javac -cp .;%MPJ_HOME%/lib/mpj.jar cMat.java
//EJECUCION: mpjrun.bat -np 4 cMat

import mpi.*;

public class cMat {

    public static void main(String[] args) throws Exception {
        
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int emisor = 0;
        int tag = 100;
        int tag2 = 200;
        int tag3 = 300;
        int unitSize = 9;

        if( rank == emisor ){

            float[] A = {1,2,3,4,5,6,7,8,9};
            float[] B = {1,0,0,0,1,0,0,0,1};

            MPI.COMM_WORLD.Send(A, 0, unitSize, MPI.FLOAT, 1, tag);
            MPI.COMM_WORLD.Send(B, 0, unitSize, MPI.FLOAT, 1, tag);
            MPI.COMM_WORLD.Send(A, 0, unitSize, MPI.FLOAT, 2, tag2);
            MPI.COMM_WORLD.Send(B, 0, unitSize, MPI.FLOAT, 2, tag2);
            MPI.COMM_WORLD.Send(A, 0, unitSize, MPI.FLOAT, 3, tag3);

        }

        if ( rank == 1 ) {

            float revbufer[] = new float[9];
            float revbufer2[] = new float[9];
            float resultado[] = new float[9];

            MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.FLOAT, emisor, tag);
            MPI.COMM_WORLD.Recv(revbufer2, 0, unitSize, MPI.FLOAT, emisor, tag);

            //Sumamos las matrices pasadas en forma de vector
            for(int i=0; i<revbufer.length; i++){
                resultado[i] = revbufer[i] + revbufer2[i];
            }

            //Mostramos por pantalla el resultado en forma de matriz
            imprimirVector(resultado, "El resultado de la suma es:");
            
        }

        if( rank == 2 ){

            float revbufer[] = new float[9];
            float revbufer2[] = new float[9];

            MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.FLOAT, emisor, tag2);
            MPI.COMM_WORLD.Recv(revbufer2, 0, unitSize, MPI.FLOAT, emisor, tag2);

            //Pasamos los vectores recibidos a matrices
            float[][] matrizA = new float[3][3];
            float[][] matrizB = new float[3][3];
            float[][] matrizResultado = new float[3][3];

            int contador = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    matrizA[i][j] = revbufer[contador];
                    matrizB[i][j] = revbufer2[contador];
                    contador++;
                }
            }

            //Multiplicamos las matrices
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    for(int k=0; k<3; k++){
                        matrizResultado[i][j] += matrizA[i][k] * matrizB[k][j];
                    }
                }
            }      

            //Mostramos por pantalla el resultado en forma de matriz
            imprimirMatriz(matrizResultado, "El resultado de la multiplicacion es:");

        }

        if( rank == 3 ){

            float revbufer[] = new float[9];

            MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.FLOAT, emisor, tag3);

            //Transponemos el vector recibido
            float[] transpuesta = new float[9];
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    transpuesta[calcularPosicion(i, j)] = revbufer[calcularPosicion(j, i)];
                }
            }

            //Devolvemos la transpuesta
            MPI.COMM_WORLD.Send(transpuesta, 0, unitSize, MPI.FLOAT, emisor, tag3);


        }

        if( rank == emisor ){
                
                float revbufer[] = new float[9];
    
                MPI.COMM_WORLD.Recv(revbufer, 0, unitSize, MPI.FLOAT, 3, tag3);
    
                //Mostramos por pantalla la transpuesta
                imprimirVector(revbufer, "El resultado de la transpuesta es:");
    
        }

        MPI.Finalize();


    }

    //Hacemos una función para imprimir el vector en forma de matriz
    public static void imprimirVector(float[] matriz, String mensaje){
        String aux = mensaje + "\n";
        for(int i=0; i<matriz.length; i++){
            aux += matriz[i] + " ";
            if(i==2 || i==5 || i==8){
                aux += "\n";
            }
        }
        System.out.println(aux);
    }

    //Hacemos una función para imprimir la matriz
    public static void imprimirMatriz(float[][] matriz, String mensaje){
        String aux = mensaje + "\n";
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                aux += matriz[i][j] + " ";
            }
            aux += "\n";
        }
        System.out.println(aux);
    }

    public static int calcularPosicion(int i, int j) {
        return (((i) * 3) + j);
    }


    
}
