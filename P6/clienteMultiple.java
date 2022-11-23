/**
 * Ejercicio 1 - Práctica 6
 * @author Manuel Pérez Ruiz
 */

import java.net.*;
import java.io.*;
/**
 * Clase clienteMultiple que lanza varias peticiones al servidor
 */
public class clienteMultiple{
    
    /**
     * Método main de la clase clienteMultiple.
     * @param args
     */
    public static void main (String[] args){
        int puerto = 2001;
        
        try{
            for(int i = 0; i < 10; i++){
                int aux = (int)(Math.random()*10);
                System.out.println("Realizando conexion...");
                Socket cable = new Socket("localhost", 2001);
                System.out.println("Realizada conexion a " + cable);
                PrintWriter salida= new PrintWriter(
                                        new BufferedWriter(
                                            new OutputStreamWriter(
                cable.getOutputStream())));
                salida.println(aux);
                salida.flush();
                System.out.println("Cerrando conexion...");
                cable.close();
            }
            

        }catch (Exception e){
            System.out.println("Error en sockets...");
        }
    
    }//main
}//Cliente_Hilos
