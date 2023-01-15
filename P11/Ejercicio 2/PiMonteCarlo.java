/**
 * Ejercicio 2 - Práctica 11
 * @author Manuel Pérez Ruiz
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Clase PiMonteCarlo que ejerce de servidor para calcular la aproximación de Pi mediante el método de MonteCarlo.
 */
public class PiMonteCarlo extends UnicastRemoteObject implements iPiMonteCarlo{
    
    /**
     * Atributo que usamos para guardar el número de puntos que caen dentro del círculo.
     */
    private int puntosDentro;

    /**
     * Atributo que usamos para guardar el número de intentos que llevamos.
     */
    private int intentos;

    /**
     * Atributo que usamos para guardar la aproximación actual de Pi.
     */
    private double aproxActual;


    /**
     * Constructor de la clase PiMonteCarlo.
     * @throws RemoteException
     */
    public PiMonteCarlo() throws RemoteException{

        puntosDentro = 0;
        intentos = 0;
        aproxActual = 0;

    }

    /**
     * Método que calcula la aproximación de Pi mediante el método de MonteCarlo.
     * @param nPuntos Número de puntos que vamos a usar para calcular la aproximación.
     * @throws RemoteException
     */
    public void calcMontecarlo(int nPuntos) throws RemoteException{

        double x, y;

        for (int i = 0; i < nPuntos; i++) {
            x = Math.random();
            y = Math.random();
            if (y <= Math.sqrt(1 - Math.pow(x, 2))) {
                this.puntosDentro++;
            }
        }
        this.intentos += nPuntos;
        aproxActual = 4 * (double) this.puntosDentro / this.intentos;

    }

    /**
     * Método que resetea los atributos de la clase.
     * @throws RemoteException
     */
    public void reset() throws RemoteException{

        puntosDentro = 0;
        intentos = 0;
        aproxActual = 0;

    }

    /**
     * Método que devuelve la aproximación actual de Pi.
     * @return Aproximación actual de Pi.
     * @throws RemoteException
     */
    public double aproxActual() throws RemoteException{

        return aproxActual;

    }

    /**
     * Método que devuelve el número de intentos que llevamos.
     * @return Número de intentos que llevamos.
     * @throws RemoteException
     */
    public void masPuntos(int nPuntos) throws RemoteException{

        calcMontecarlo(nPuntos);

    }

    /**
     * Método main de la clase PiMonteCarlo.
     * @return Número de puntos que caen dentro del círculo.
     * @throws RemoteException
     */
    public static void main(String[] args) {
            
            try {
                iPiMonteCarlo pi = new PiMonteCarlo();
                Naming.bind("Servidor", pi);
                System.out.println("Servidor preparado");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

    }




}
