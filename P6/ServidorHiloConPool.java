/**
 * Ejercicio 1 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

import java.net.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.*;

public class ServidorHiloConPool implements Runnable{

    Socket enchufe;

    public ServidorHiloConPool(Socket s){
        enchufe = s;
    }

    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(
                                        new InputStreamReader(
                                            enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();

            for(j=1; j<=20; j++){
                System.out.println("El hilo "+this.getName()+" escribiendo el dato "+i);
                Thread.sleep(1000);
            }

            enchufe.close();
            System.out.println("El hilo "+this.getName()+"cierra su conexion...");

        } catch(Exception e) {System.out.println("Error...");}
    }//run

public static void main (String[] args){
    
    int i;
    int puerto = 2001;
        
    try{
        ServerSocket chuff = new ServerSocket (puerto, 3000);

        while (true){
            
            ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 12, 5000, 0L, new LinkedBlockingQueue<Runnable>());

            System.out.println("Esperando solicitud de conexion...");
            Socket cable = chuff.accept();
            System.out.println("Recibida solicitud de conexion...");
            new Servidor_Hilos(cable);
        }//while
    } catch (Exception e){System.out.println("Error en sockets...");}
}//main

}//Servidor_Hilos
