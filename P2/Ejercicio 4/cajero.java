
/**
 * @author Manuel Pérez Ruiz
 * Clase Cajero. Cajero automático.
 */

public class cajero implements Runnable{

    /**
     * @param cuenta Cuenta corriente.
     * @param tipo Tipo de operación.
     * @param cantidad Cantidad a operar.
     */
    private cuentaCorriente cuenta;
    private int operacion;
    private double cantidad;

    /**
     * Constructor de la clase cajero.
     */
    public cajero(cuentaCorriente cuenta, int operacion, double cantidad){
        this.cuenta = cuenta;
        this.operacion = operacion;
        this.cantidad = cantidad;
    }
    
    /**
     * Método que ejecuta el cajero.
     */
    public void run(){
        if(operacion == 1){
            cuenta.deposito(cantidad);
        }else{
            cuenta.reintegro(cantidad);
        }
    }
    

}
