/**
 * Ejercicio 1 - Examen Junio 2023
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class cEstado {
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry();
            iEstado interfaz = (iEstado) registro.lookup("Estado");

            int[] v = {-1, 0, 3, -4, 0, 6};

            System.out.println("Vector X: " + java.util.Arrays.toString(v));
            System.out.println("Transformando remotamente...");

            int[] vCambio = interfaz.siguienteEstado(v);

            System.out.println("Vector Y: " + java.util.Arrays.toString(vCambio));
        } catch (Exception e) {

            System.err.println("Error en el cliente: " + e.toString());
            e.printStackTrace();

        }
    }
}
