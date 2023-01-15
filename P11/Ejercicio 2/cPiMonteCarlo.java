import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Ejercicio 2 - Práctica 11
 * @author Manuel Pérez Ruiz
 */

 /**
  * Clase cPiMonteCarlo que ejerce de cliente para calcular la aproximación de Pi mediante el método de MonteCarlo.
  */
public class cPiMonteCarlo {

    /**
     * Método main de la clase cPiMonteCarlo.
     * @param args
     */
    public static void main(String[] args) {
        
        int opcion = 0;
        iPiMonteCarlo RefObRemoto = null;
        Scanner s;
        
        System.out.println("Seleccione una opcion: \n 1. Anadir puntos \n 2. Aproximacion actual \n 3. Resetear \n 4. Salir");
        s = new Scanner(System.in);
        opcion = s.nextInt();

        try{
            RefObRemoto = (iPiMonteCarlo) java.rmi.Naming.lookup("//localhost/Servidor");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    System.out.println("Introduzca el numero de puntos a anadir: ");
                    int nPuntos = s.nextInt();
                    try {
                        RefObRemoto.masPuntos(nPuntos);
                    } catch (RemoteException e) {
                        System.out.println("Error al anadir puntos");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Aproximacion actual: " + RefObRemoto.aproxActual());
                    } catch (RemoteException e) {
                        System.out.println("Error al obtener la aproximacion actual");
                    }
                    break;
                case 3:
                    try {
                        RefObRemoto.reset();
                    } catch (RemoteException e) {
                        System.out.println("Error al resetear");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            System.out.println("Seleccione una opcion: \n 1. Anadir puntos \n 2. Aproximacion actual \n 3. Resetear \n 4. Salir");
            opcion = s.nextInt();
        }



    }
    
}
