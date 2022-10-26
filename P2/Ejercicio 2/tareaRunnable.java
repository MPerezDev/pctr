/**
 * @author Manuel Pérez Ruiz
 */

/**
 * Clase tareaRunnable que incrementa nVueltas veces el valor de n.
 */
public class tareaRunnable implements Runnable{

    /**
     * Atributos de la clase tareaRunnable.
     * @param nVueltas Número de vueltas que realiza la tarea.
     * @param n Valor de n.
     * @param tipoTarea Atributo con el que el método run sabe si tiene que incrementar o decrementar el valor de n.
     */

    public static int n=0;
    private int tipoTarea;
    private int nVueltas;

    /**
     * Constructor de la clase tareaRunnable.
     */

    public tareaRunnable(int tipoTarea, int nVueltas){
        this.tipoTarea = tipoTarea;
        this.nVueltas = nVueltas;
    }

    /**
     * Método run de la clase tareaRunnable. Incrementa o decrementa nVueltas veces el valor de n dependiendo del valor del atributo tipoTarea.
     */

    public void run(){
        for(int i=0; i<nVueltas; i++){
            if(tipoTarea == 0){
                n++;
            }else{
                n--;
            }
        }
    }

    
}
