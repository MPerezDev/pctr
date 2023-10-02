/** Examen Septiembre 2023 - PCTR
 * @author Manuel Pérez Ruiz - 44065118Q
 */

 //Compilación: javac -cp .;%MPJ_HOME%/lib/mpj.jar calIntegral.java
 //Ejecución: mpjrun.bat -np 6 calIntegral

import mpi.*;

public class calIntegral {
    static double Calcular(double x, int rank){
        if(rank == 1) return (double)(4/(1+Math.pow(x, 3)));
        if(rank == 2) return (double)(Math.sin(x) + 3);
        if(rank == 3) return (double)(Math.pow(Math.E,x));
        if(rank == 5) return (double)(x);
        return 0.0;
    }

    public static void main(String[] args) {
        
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int factor[] = new int[1];
        double integral[] = new double[1];
        double sumatorio[] = new double[1];
         double resFinal[] = new double[2];



        if(rank == 0){
            factor[0] = 3000000;
            System.out.println("Master difundiendo factor de precision: " + factor[0]);
        }

        MPI.COMM_WORLD.Bcast(factor, 0, 1, MPI.INT, 0);

        if(rank == 0){

            double limiteSuperior[] = {3, 8, 1, 3};

            MPI.COMM_WORLD.Send(limiteSuperior, 0, 1, MPI.DOUBLE, 1, 0);
            MPI.COMM_WORLD.Send(limiteSuperior, 1, 1, MPI.DOUBLE, 2, 0);
            MPI.COMM_WORLD.Send(limiteSuperior, 2, 1, MPI.DOUBLE, 3, 0);
            MPI.COMM_WORLD.Send(limiteSuperior, 3, 1, MPI.DOUBLE, 5, 0);

            integral[0] = 1;

        }
        
        if(rank != 0 && rank != 5){

            double solParcial[] = new double[1];
            MPI.COMM_WORLD.Recv(solParcial, 0, 1, MPI.DOUBLE, 0, 0);

            System.out.println("PRUEBAS SLAVE " + rank + ": " + factor[0] + " [0.0, " + solParcial[0] + "]");
            double longitud = solParcial[0]/factor[0];

            for(int i = 1; i < factor[0]; i++){
                if(rank!=4){
                    integral[0] += Calcular(longitud * i, rank) * longitud;
                }else{ 
                    integral[0] = 1;
                }
                
            }
            System.out.println("Soy el proceso " + rank + " y mi integracion es: " + integral[0]);
        }

        //if(rank != 0 && rank != 5){
            System.out.println("Valor de proceso " + rank + " valor " + integral[0]);
            MPI.COMM_WORLD.Reduce(integral, 0, sumatorio, 0, 1, MPI.DOUBLE, MPI.PROD, 4);
            System.out.println("Valor de reduccion " + rank + " valor " + sumatorio[0]);

        //}
            

        if(rank == 4){
            System.out.println("Soy el proceso " + rank + " y la reduccion es: " + sumatorio[0]);
            MPI.COMM_WORLD.Send(sumatorio, 0, 1, MPI.DOUBLE, 0, 20);

        }

        if(rank == 5){

            double resParcial[] = new double[1];
            MPI.COMM_WORLD.Recv(resParcial, 0, 1, MPI.DOUBLE, 0, 0);

            System.out.println("PRUEBAS SLAVE " + rank + ": " + factor[0] + " [0.0, " + resParcial[0] + "]");
            double solParcial[] = new double[1];
            double longitud = resParcial[0]/factor[0];

            for(int i = 1; i < factor[0]; i++){
                solParcial[0] += Calcular(longitud * i, rank) * longitud;
            }

            System.out.println("Soy el proceso " + rank + " y mi integracion es: " + solParcial[0]);
            MPI.COMM_WORLD.Send(solParcial, 0, 1, MPI.DOUBLE, 0, 10);      
            
        }

        if(rank == 0){
            MPI.COMM_WORLD.Recv(resFinal, 0, 1, MPI.DOUBLE, 5, 10);
        }

        if(rank == 0){

            
            boolean P;
            double I;

            MPI.COMM_WORLD.Recv(resFinal, 1, 1, MPI.DOUBLE, 4, 20);
            

            I = resFinal[0] + resFinal[1];

            for(int i = 0; i < 2; i++){
                System.out.println("Parte " + i + ": " + resFinal[i]);
            }
            System.out.println("Resultado Final (master), I = " + I);

            P = (Math.floor(I)/2  == 0);

            System.out.println("La paridad de I es = " + P);
            
        }

        MPI.Finalize();
    }

}
