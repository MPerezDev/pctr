import java.util.concurrent.locks.*;

public class myBarrier {
    
    private int nBarrera;
    private int contador;
    final ReentrantLock cerrojo = new ReentrantLock();
    final Condition condicion = cerrojo.newCondition();
    
    public myBarrier(int n) {
        this.nBarrera = n;
        this.contador = n;
    }

    public void toWaitOnBarrier() {

        cerrojo.lock();
        try {
            while (nBarrera == 0) {
                try{
                    condicion.await();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
            }
            nBarrera--;
            condicion.signalAll();
        } finally{
            cerrojo.unlock();
        }

        
    }

    public void resetBarrier() {
        nBarrera = contador;
    }
    
    

}