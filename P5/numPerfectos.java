import java.util.Scanner;

public class numPerfectos {

    public static boolean esPerfecto(int n) {
        int suma = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                suma += i;
            }
        }
        return suma == n;
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int n = 0;
        int cont = 0;

        do{
            System.out.println("Introduzca el limite superior del rango de comprobacion: ");
            n = in.nextInt();
        }while(n<0);

        for (int i = 1; i <= n; i++) {
            if (esPerfecto(i)) {
                cont++;
            }
        }
        System.out.println("Hay " + cont + " numeros perfectos entre 1 y " + n);
    }

    
}
