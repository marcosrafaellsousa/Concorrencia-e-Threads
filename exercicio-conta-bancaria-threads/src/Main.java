import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final int quantidadeDeThreads = 25;
        final Conta conta = new Conta(1,0);
        final ReentrantLock reentrantLock = new ReentrantLock();
        ArrayList<Thread> listaDeThreads = new ArrayList<>();
        for (int i=0; i < quantidadeDeThreads; i++) {
            Thread thread = new Thread(new CaixaBancario(conta, reentrantLock));
            listaDeThreads.add(thread);
        }

        for (int i=0; i < quantidadeDeThreads; i++) {
            listaDeThreads.get(i).start();
        }

        for (int i=0; i < quantidadeDeThreads; i++) {
            listaDeThreads.get(i).join();
        }

        System.out.println("Valor final: " + conta.getValor());
    }
}
