import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class cCuaternion {
    
    public static void main(String[] args) {


        String [] services = null;
        ICuaternion RefObRemoto = null;
        Scanner s = new Scanner(System.in);



        try{
            Registry registry = LocateRegistry.getRegistry();
            services = registry.list();
        }catch (RemoteException e) {
            System.out.println("Error al listar los servicios");
        }
        

        System.out.println("Servicios disponibles: ");
        for (String service : services) {
            System.out.println(service);
        }

        System.out.println("Introduzca el primer cuaternion: ");
        float[] q1 = new float[4];
        for (int i = 0; i < 4; i++) {
            q1[i] = s.nextFloat();
        }
        System.out.println("Introduzca el segundo cuaternion: ");
        float[] q2 = new float[4];
        for (int i = 0; i < 4; i++) {
            q2[i] = s.nextFloat();
        }

        try { 
            
            System.out.println("El primer cuaternion es: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(q1[i] + " ");
            }
            System.out.println();

            System.out.println("El segundo cuaternion es: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(q2[i] + " ");
            }
            System.out.println();

            RefObRemoto = (ICuaternion) Naming.lookup("suma");
            float[] suma = RefObRemoto.sumCuaternion(q1, q2);
            System.out.println("La suma es: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(suma[i] + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error al sumar");
        }

        try {

            RefObRemoto = (ICuaternion) Naming.lookup("conjugado");
            float[] conjugado = RefObRemoto.conCuaternion(q1);
            System.out.println("El conjugado es: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(conjugado[i] + " ");
            }
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error al calcular el conjugado");
        }

        System.out.println("Introduzca un escalar: ");
        float escalar = s.nextFloat();

        try {

            RefObRemoto = (ICuaternion) Naming.lookup("escalado");
            float[] escalado = RefObRemoto.xCuaternion(q1, escalar);
            System.out.println("El cuaternion escalado es: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(escalado[i] + " ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error al calcular el cuaternion escalado");
        }

        try {

            RefObRemoto = (ICuaternion) Naming.lookup("traza");
            float traza = RefObRemoto.tCuaternion(q1);
            System.out.println("La traza es: " + traza);
        } catch (Exception e) {
            System.out.println("Error al calcular la traza");
        }

        s.close();
            
        
        
    }

}
