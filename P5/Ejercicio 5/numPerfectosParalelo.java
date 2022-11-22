/**
 * Ejercicio 5 - Práctica 5
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.*;
import java.util.*;


/**
 * Clase numPerfectosParalelo que calcula los números perfectos entre 1 y un número
 */
public class numPerfectosParalelo implements Callable<Integer>{
    /**
     * Atributos de la clase numPerfectosParalelo
     * @param inicio inicio del rango
     * @param fin fin del rango
     */

    public int inicio;
    public int fin;

    /**
     * Constructor de la clase numPerfectosParalelo
     * @param inicio inicio del rango
     * @param fin fin del rango
     */
    public numPerfectosParalelo(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }
    
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
     * Método call de la clase numPerfectosParalelo
     */
    public Integer call() {
        int total = 0;
        for(int i = inicio; i < fin; i++){
            if(esPerfecto(i))
                if(i != 0){
                    total++;
                }
            }
        return total;
    }

    /**
     * Método main de la clase numPerfectosParalelo
     * @param args
     */
    public static void main(String[] args) throws Exception{

        long rango = Long.parseLong(args[0]);

        List<Future<Integer>> list = Collections.synchronizedList(new ArrayList<Future<Integer>>());

        int nCores = Runtime.getRuntime().availableProcessors();
        int aux = (int)rango/nCores;
        int cont = 0;

        long tInicio = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(nCores);
        for(int i = 0; i < nCores; i++){
            
            list.add(executor.submit(new numPerfectosParalelo(aux * i, aux * (i+1))));

        }
        executor.shutdown();
        while(!executor.isTerminated()){}
        long tFin = System.nanoTime();

        for(Future<Integer> future : list){
            cont += future.get();
        }

        System.out.println("Hay " + cont + " numeros perfectos entre 1 y " + rango);
        System.out.println("Tiempo total: " + (tFin - tInicio)/(float)1.0e9 + " segundos");


        
    }

    


}
