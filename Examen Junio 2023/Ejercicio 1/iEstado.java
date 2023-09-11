/**
 * Ejercicio 1 - Examen Junio 2023
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface iEstado extends Remote {
    int[] siguienteEstado(int[] x) throws RemoteException;
}
