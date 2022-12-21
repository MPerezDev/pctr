/**
 * @author Manuel Pérez Ruiz
 * Clase CuentaCorriente. Cuenta corriente de un cliente.
 */


public class cuentaCorriente {
    
    /**
     * @param saldo Saldo de la cuenta corriente.
     * @param numeroCuenta Número de cuenta corriente.
     */

    private double saldo;
    private int numeroCuenta;

    /**
     * Constructor de la clase cuentaCorriente.
     */

    public cuentaCorriente(double saldo, int numeroCuenta){
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Método que incrementa el saldo de la cuenta corriente.
     */

    public void deposito(double cantidad){
        int aux=0;


        for(int i = 0; i < cantidad; i++){
            aux++;
            saldo++;
        }
        saldo += (cantidad - aux);
    }

    /**
     * Método que decrementa el saldo de la cuenta corriente.
     */

    public void reintegro(double cantidad){
        int aux=0;

        for(int i = 0; i < cantidad; i++){
            aux++;
            saldo--;
        }

        saldo -= (cantidad - aux);

    }

    /**
     * Método que devuelve el saldo de la cuenta corriente.
     */

    public double saldo(){
        return saldo;
    }


}
