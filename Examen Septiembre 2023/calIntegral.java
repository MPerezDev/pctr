/** 
 * Prueba examen Sep 2023 - Matanza de cerdos
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */

import mpi.*;



public class calIntegral {
    
    public static void main(String[] args) throws Exception{

        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int reductor = 4;
        int factor[] = new int[1];

        if(rank==emisor){
            factor[0] = 4000000;
            System.out.println("Master difundiendo factor de precision: " + factor);
        }
        
        MPI.COMM_WORLD.Bcast(factor, 0, 1, MPI.INT, emisor);

        if(rank==emisor){
            
            double limites[] = {3,8,1,3};
            //EL proceso master manda los límites de integración a los procesos esclavos
            MPI.COMM_WORLD.Send(limites, 0, 1, MPI.DOUBLE, 1, 0);
            MPI.COMM_WORLD.Send(limites, 1, 1, MPI.DOUBLE, 2, 0);
            MPI.COMM_WORLD.Send(limites, 2, 1, MPI.DOUBLE, 3, 0);
            MPI.COMM_WORLD.Send(limites, 3, 1, MPI.DOUBLE, 5, 0);

        }
        
        if(rank!=emisor){
            double limites[] = new double[1];
            MPI.COMM_WORLD.Recv(limites, 0, 1, MPI.DOUBLE, emisor, 0);
            double resultadoParcial = 0;
            double base = (limites[0]/factor[0]);

            for(int i=0; i<factor[0]; i++){

                if(rank==1){
                    
                }
            }
                

        }
        

        if(rank==reductor){

        }

        
    }

}
