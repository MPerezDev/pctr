/**
 * Ejercicio 1 - Práctica 11
 * @author Manuel Pérez Ruiz
 */

import java.rmi.*;


/**
 * Clase cBonoLoto que ejerce de cliente para jugar a la BonoLoto, el cual envía una apuesta al servidor y recibe un bool indicando si ha acertado o no.
 */
public class cBonoLoto {
    
    /**
     * Método main de la clase cBonoLoto.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        
        iBonoLoto Servidor =  (iBonoLoto)Naming.lookup("//localhost/Servidor");
        
        int[] apuesta = {1,2,3,4,5,6};

        if(Servidor.compApuesta(apuesta) == true){
            System.out.println("Has acertado");
        }else{
            System.out.println("No has acertado");
        }


    }

}
