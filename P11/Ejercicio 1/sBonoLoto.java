import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase sBonoloto que ejerce de servidor para jugar a la BonoLoto, generando seis números aleatorios entre 1 y 49.
 */
public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto{
    
    /**
     * Atributo que usamos para guardar los números de la BonoLoto.
     */
    private int[] bonoloto = new int[6];

    /**
     * Constructor de la clase sBonoLoto.
     * @throws RemoteException
     */
    public sBonoLoto() throws RemoteException{
        for(int i = 0; i < 6; i++){
            bonoloto[i] = (int)(Math.random()*49+1);
        }
    }

    /**
     * Método que comprueba si la apuesta es correcta.
     * @param apuesta Combinación de números que se han apostado.
     * @return Devuelve true si se ha acertado la apuesta y false en caso contrario.
     * @throws RemoteException
     */
    public boolean compApuesta(int[] apuesta) throws RemoteException{
        int aciertos = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(apuesta[i] == bonoloto[j]){
                    aciertos++;
                }
            }
        }
        if(aciertos == 6){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Método main de la clase sBonoLoto.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
            
            try{
                iBonoLoto Servidor = new sBonoLoto();
                Naming.bind("Servidor", Servidor);
                System.out.println("Servidor Remoto Preparado");
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
            
    }

    /**
     * Método que reinicia el servidor, generando seis números aleatorios entre 1 y 49.
     * @throws RemoteException
     */
    public void resetServidor() throws RemoteException {
        for(int i = 0; i < 6; i++){
            bonoloto[i] = (int)(Math.random()*49+1);
        }  
    }


}
