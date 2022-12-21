import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Ejercicio 3 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase lectorEscritor que se basa en un monitor de alto nivel, con cerrojos ReentrantLock y variables de condición modeladas con la 
 * interfaz Condition.
 */
public class lectorEscritor {
    
    /**
     * Variable que almacena el número de lectores que están leyendo.
     */
    private static int lectores = 0;

    /**
     * Variable que indica si hay un escritor activo.
     */
    private static boolean escribiendo = false;

    /**
     * Variable que almacena el cerrojo.
     */
    private ReentrantLock cerrojo = new ReentrantLock();

    /**
     * Variable de condición "escribiendo".
     */
    private Condition condicionEscribiendo;

    /**
     * Variable de condición "lectores".
     */
    private Condition condicionLectores;

    /**
     * Constructor de la clase.
     */
    public lectorEscritor(){
        condicionEscribiendo = cerrojo.newCondition();
        condicionLectores = cerrojo.newCondition();
    }

    /**
     * Método que simula la lectura de un recurso.
     * @throws InterruptedException
     */
    public void iniciaLectura() throws InterruptedException{
        cerrojo.lock();
        try{
            while(escribiendo) condicionLectores.await();
            lectores++;
            condicionLectores.signalAll();
        }finally{
            cerrojo.unlock();
        }
    }

    /**
     * Método que simula la finalización de la lectura de un recurso.
     * @throws InterruptedException
     */
    public void acabarLectura() throws InterruptedException{
        cerrojo.lock();
        try{
            lectores--;
        if(lectores == 0) condicionEscribiendo.signalAll();
        }finally{
            cerrojo.unlock();
        }
    }

    /**
     * Método que simula la escritura de un recurso.
     * @throws InterruptedException
     */
    public void iniciarEscritura() throws InterruptedException{
        cerrojo.lock();
        try{
            while(lectores > 0 || escribiendo) condicionEscribiendo.await();
            escribiendo = true;
        }finally{
            cerrojo.unlock();
        }
    
    }

    /**
     * Método que simula la finalización de la escritura de un recurso.
     * @throws InterruptedException
     */
    public void acabarEscritura() throws InterruptedException{
        cerrojo.lock();
        try{
            escribiendo = false;
            condicionLectores.signalAll();     
        }finally{
            cerrojo.unlock();
        }
    }


}
