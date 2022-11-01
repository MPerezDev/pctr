
/**
 * @author Manuel Pérez Ruiz
 * Clase RedCajeros. Red de cajeros automáticos.
 */

public class redCajeros{

    /**
     * Método main de la clase redCajeros.
     * @param args
     * @param cuenta Cuenta corriente.
     */

    public static void main(String[] args) {
        cuentaCorriente cuenta = new cuentaCorriente(0, 123456789);

        Runnable cajero1 = new cajero(cuenta, 1, 100000.5);
        Runnable cajero2 = new cajero(cuenta, 0, 100000.5);

        Thread hebra1 = new Thread(cajero1);
        Thread hebra2 = new Thread(cajero2);

        hebra1.start();
        hebra2.start();
        try{
            hebra1.join();
            hebra2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + cuenta.saldo());


    }
    
    

}
