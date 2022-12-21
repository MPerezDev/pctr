/**
 * Ejercicio 1 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.locks.ReentrantLock;

 /**
  * CLase cCRL. Cuenta corriente de un cliente que usa cerrojos de tipo ReentrantLock.
  */
class cCRL {

    /**
     * Atributo que representa el saldo de la cuenta corriente.
     */
    private double saldo;

    /**
     * Atributo que representa el número de cuenta corriente.
     */
    private int numeroCuenta;

    /** 
     * Atributo que representa el cerrojo de la cuenta corriente.
     */
    private ReentrantLock cerrojo;
    

    /**
     * Constructor de la clase cuentaCorriente.
     */
    public cCRL(double saldo, int numeroCuenta){
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.cerrojo = new ReentrantLock();
    }

    /**
     * Método que incrementa el saldo de la cuenta corriente.
     */
    public void deposito(double cantidad){
        cerrojo.lock();
        try{
            int aux=0;

            for(int i = 0; i < cantidad; i++){
                aux++;
                saldo++;
            }
            saldo += (cantidad - aux);
        }finally{
            cerrojo.unlock();
        }

    }

    /**
     * Método que decrementa el saldo de la cuenta corriente.
     */
    public void reintegro(double cantidad){
        cerrojo.lock();
        try{
            int aux=0;

            for(int i = 0; i < cantidad; i++){
                aux++;
                saldo--;
            }
            saldo -= (cantidad - aux);
        }finally{
            cerrojo.unlock();
        }

    }

    /**
     * Método que devuelve el saldo de la cuenta corriente.
     */
    public double saldo(){
        cerrojo.lock();
        try{
            return saldo;
        }finally{
            cerrojo.unlock();
        }
    }

}
