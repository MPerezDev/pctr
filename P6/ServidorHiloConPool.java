/**
 * Ejercicio 1 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

import java.net.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;


/**
 * Clase ServidorHiloConPool que implementa la interfaz Runnable.
 */
public class ServidorHiloConPool implements Runnable{

    /**
     * Atributo de tipo Socket que representa el socket del cliente.
     */
    Socket enchufe;

    /**
     * Constructor de la clase ServidorHiloConPool.
     * @param s Socket del cliente.
     */
    public ServidorHiloConPool(Socket s){
        enchufe = s;
    }

    /**
     * Método run de la clase ServidorHiloConPool. 
     */
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(
                                        new InputStreamReader(
                                            enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();

            for(j=1; j<=20; j++){
                System.out.println("El hilo " + Thread.currentThread().getName() +  " escribiendo el dato "+i);
                Thread.sleep(1000);
            }

            enchufe.close();
            System.out.println("El hilo " + Thread.currentThread().getName() + "cierra su conexion...");

        } catch(Exception e) {System.out.println("Error...");}
    }//run
/**
 * Método main de la clase ServidorHiloConPool.
 * @param args
 */
public static void main (String[] args){
    
    int i;
    int puerto = 2001;
        
    try{
        ServerSocket chuff = new ServerSocket (puerto, 3000);

        while (true){
            
            ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

            System.out.println("Esperando solicitud de conexion...");
            Socket cable = chuff.accept();

            System.out.println("Recibida solicitud de conexion..."); 
            pool.execute(new ServidorHiloConPool(cable));
        }//while
    } catch (Exception e){System.out.println("Error en sockets...");}
}//main

}//Servidor_Hilos
