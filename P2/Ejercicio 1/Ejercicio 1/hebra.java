
/**
 * @author Manuel Pérez Ruiz
 * Clase hebra que incrementa nVueltas veces el valor de n.
 * 
 */

public class hebra extends Thread{

    /**
     * Atributos de la clase hebra.
     * @param nVueltas Número de vueltas que realiza la hebra.
     * @param n Valor de n.
     * @param tipoHebra Atributo con el que el método run sabe si tiene que incrementar o decrementar el valor de n.
     */

    private int tipoHebra;
    public static int n=0;
    private int nVueltas;

    /**
     * Constructor de la clase hebra.
     */

    public hebra(int tipoHebra, int nVueltas){
        this.tipoHebra = tipoHebra;
        this.nVueltas = nVueltas;
    }

    /**
     * Método run de la clase hebra. Incrementa o decrementa nVueltas veces el valor de n dependiendo del valor del atributo tipoHebra.
     */

    public void run(){
        for(int i=0; i<nVueltas; i++){
            if(tipoHebra == 0){
                n++;
            }else{
                n--;
            }
        }
    }

    


}