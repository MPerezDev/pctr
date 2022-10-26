import java.util.*;
import java.lang.Math;


public class NewtonRaphson {

    public static void main(String[] args) {
        
        
        Scanner in = new Scanner(System.in);
        int x = 0;
        int iteracion;
        double posIni;
        

        do{
            System.out.println("Seleccione la operacion deseada: ");
            System.out.println("0. Cos(x) - x^3");
            System.out.println("1. x^2 -5");
            x = in.nextInt();
        }while(x<0 || x>1);

        do{

            System.out.println("Introduzca el numero de iteraciones: ");
            iteracion = in.nextInt();
            System.out.println("Introduzca la x0: ");
            posIni = in.nextDouble();

        }while((x==0 && (0 > posIni || posIni > 1)) || (x==1 && (posIni < 2 || posIni > 3)) && iteracion<0);
        
        for(int i=0;i<iteracion;i++){

            posIni = posIni - f(posIni,x)/p(posIni,x);
            System.out.println("Iteracion nº" + (i+1) + ": " + posIni);

        }

        System.out.println("El resultado final es " + posIni);

        
        in.close();

    }

    public static double f(double d, int x){

        if(x==0){
            return Math.cos(d) - Math.pow(d, 3);
        }else{
            return Math.pow(d, 2) - 5;
        }
    
    }

    public static double p(double d, int x){

        if(x==0){
            return -Math.sin(d) - 3*Math.pow(d, 2);
        }else{
            return 2*d;
        }

    }
    

}