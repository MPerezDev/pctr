import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 5 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

 // Versión de la integración paralela de Monte Carlo para aproximar la integral definida en la función f(x) = cos(x) en [0,1] utilizando tareas Callable y la interfaz Future.
public class integCallable implements Callable<Double>{

    /**
     * Atributos de la clase integCallable
     * @param inicio inicio del rango
     * @param fin fin del rango
     * @param n número de puntos
     */
    public double inicio;
    public double fin;
    public int n;

    /**
     * Constructor de la clase integCallable
     * @param inicio inicio del rango
     * @param fin fin del rango
     * @param n número de puntos
     */
    public integCallable(double inicio, double fin, int n) {
        this.inicio = inicio;
        this.fin = fin;
        this.n = n;
    }

    /**
     * Método call de la clase integCallable
     */
    public Double call() {
        double x, y, area, suma = 0;
        for (int i = 0; i < n; i++) {
            x = Math.random() * (fin - inicio) + inicio;
            y = Math.random();
            if (y <= Math.cos(x)) {
                suma++;
            }
        }
        area = (fin - inicio) * suma / n;
        return area;
    }
    /**
     * Método main de la clase integCallable
     * @param args
     */
    public static void main(String[] args) {
        int n = 100000;
        int nTareas = 100;
        double inicio = 0;
        double fin = 1;
        double area = 0;
        double paso = (fin - inicio) / nTareas;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(6,12,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        
        List<Future<Double>> lista = new ArrayList<Future<Double>>();
        for (int i = 0; i < nTareas; i++) {
            Callable<Double> tarea = new integCallable(inicio + i * paso, inicio + (i + 1) * paso, n);
            Future<Double> future = pool.submit(tarea);
            lista.add(future);
        }


        for (Future<Double> future : lista) {
            try {
                area += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        System.out.println("El área es: " + area);
    }
    
}
