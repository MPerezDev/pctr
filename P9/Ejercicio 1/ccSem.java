/**
 * Ejercicio 1 - Práctica 9
 * @author Manuel Pérez Ruiz
 */

import java.util.concurrent.Semaphore;

/**
 * CLase ccSem. Cuenta corriente de un cliente que usa semáforos.
 */
public class ccSem {
    
    /**
     * Atributo que representa el saldo de la cuenta corriente.
     */
    private double saldo;

    /**
     * Atributo que representa el número de cuenta corriente.
     */
    private int numeroCuenta;

    /**
     * Atributo que representa el semáforo de la cuenta corriente.
     */
    private Semaphore semaforo;
 
    /**
     * Constructor de la clase cuentaCorriente.
     */
    public ccSem(double saldo, int numeroCuenta){
        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.semaforo = new Semaphore(1);
    }
 
    /**
     * Método que incrementa el saldo de la cuenta corriente.
     */
    public void deposito(double cantidad){
        try{semaforo.acquire();}catch(InterruptedException e){}
        try{
            int aux=0;
 
            for(int i = 0; i < cantidad; i++){
                aux++;
                saldo++;
            }
            saldo += (cantidad - aux);
        }finally{
            semaforo.release();
        }
    }
 
    /**
     * Método que decrementa el saldo de la cuenta corriente.
     */
    public void reintegro(double cantidad){
        try{semaforo.acquire();}catch(InterruptedException e){}
        try{
            int aux=0;
 
            for(int i = 0; i < cantidad; i++){
                aux++;
                saldo--;
            }
 
            saldo -= (cantidad - aux);
        }finally{
            semaforo.release();
        }
 
    }

    /**
     * Método que devuelve el saldo de la cuenta corriente.
     */
    public double saldo(){
        try{semaforo.acquire();}catch(InterruptedException e){}
        try{
            return saldo;
        }finally{
            semaforo.release();
        }
    }

}
