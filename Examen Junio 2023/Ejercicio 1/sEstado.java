/**
 * Ejercicio 1 - Examen Junio 2023
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class sEstado implements iEstado {
    public int[] siguienteEstado(int[] v) throws RemoteException {
        int[] vCambio = new int[v.length];

        System.out.println("Servidor ha recibido el vector: [" + v[0] + ", " + v[1] + ", " + v[2] + ", " + v[3] + ", " + v[4] + ", " + v[5] + "]");

        for (int i = 0; i < v.length; i++) {

            if(i == 0){
                
                vCambio[i] = v[i] + v[i + 1];

            }else if(i == v.length - 1){

                vCambio[i] = v[i-1] + v[i];

            }else{

                vCambio[i] = v[i-1] + v[i] + v[i+1];

            }
        }
        System.out.println("Servidor enviando vector transformado...");
        return vCambio;
    }

    public static void main(String[] args) {

        try {
            sEstado server = new sEstado();
            iEstado interfaz = (iEstado) UnicastRemoteObject.exportObject(server, 0);

            Registry registro = LocateRegistry.getRegistry();
            registro.bind("Estado", interfaz);

            System.out.println("Servidor en ejecucion y a la espera ....");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
