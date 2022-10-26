import java.util.*;
import java.lang.Math;


public class montecarlo {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int x,iteracion, cont = 0;
        double coorx, coory;

        do{
            System.out.println("Seleccione la operacion deseada: ");
            System.out.println("0. sin");
            System.out.println("1. x");
            x = in.nextInt();
        }while(x<0 || x>1);

        do{
            System.out.println("Introduzca el numero de iteraciones: ");
            iteracion = in.nextInt();
        }while(iteracion<0);
        

        for(int i=0;i<=iteracion;i++){

            coorx = Math.random();
            coory = Math.random();
            if(coory <= f(coorx,x)){
                cont++;
            }

        }

        double d = (double)cont/iteracion;

        System.out.println("La aproximación es " + d);


        in.close();

    }

    public static double f(double a,int x){
        if(x==0){
            return Math.sin(a);
        }else{
            return a;
        }
    }

}
