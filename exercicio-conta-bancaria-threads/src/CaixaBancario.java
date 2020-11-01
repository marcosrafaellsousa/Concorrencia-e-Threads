import java.util.concurrent.locks.ReentrantLock;

public class CaixaBancario implements Runnable{

    private Conta contaBancaria;
    private ReentrantLock reentrantLock;

    public CaixaBancario (Conta conta, ReentrantLock reentrantLock) {
        this.contaBancaria = conta;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            //print do valor para acompanhamento
            System.out.println(this.contaBancaria.depositar(500));
        } finally {
            reentrantLock.unlock();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reentrantLock.lock();
        try {
            //print do valor para acompanhamento
            System.out.println(this.contaBancaria.sacar(500));
        } finally {
            reentrantLock.unlock();
        }
    }
}