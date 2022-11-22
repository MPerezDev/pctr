/**
 * Ejercicio 5 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

import java.util.Scanner;

/**
 * Clase numPerfectos que calcula los números perfectos entre 1 y un número
 * 
 */
public class numPerfectos {

    /**
     * Método que calcula si un número es perfecto
     * @param n número a comprobar
     * 
     */
    public static boolean esPerfecto(int n) {
        int suma = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                suma += i;
            }
        }
        return suma == n;
    }

    /**
     * Método main de la clase numPerfectos
     * @param args
     */
    public static void main(String[] args) {

        long rango = Long.parseLong(args[0]);
        int cont = 0;

        long tInicio = System.nanoTime();
        for (int i = 1; i <= rango; i++) {
            if (esPerfecto(i)) {
                cont++;
            }
        }
        long tFin = System.nanoTime();

        System.out.println("Hay " + cont + " numeros perfectos entre 1 y " + rango);
        System.out.println("Tiempo de ejecucion: " + (tFin - tInicio)/1e9 + "s");
    }

    
}
