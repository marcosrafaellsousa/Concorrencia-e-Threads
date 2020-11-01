import java.util.ArrayList;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Vector vector = new Vector();
        ArrayList<Thread> listaDeThreads = new ArrayList();

        int limiteMinimo = 1000000;
        int limiteMaximo = 30000000;

        //Dispara as threads da primeira faixa
        for (int i = limiteMinimo; i < limiteMaximo; i++) {
            Thread thread = new VerificaPrimo(vector, i);
            thread.start();
            listaDeThreads.add(thread);
        }

        limiteMinimo = 90000000;
        limiteMaximo = 120000000;

        //Dispara as threads da segunda faixa
        for (int i = limiteMinimo; i < limiteMaximo; i++) {
            Thread thread = new VerificaPrimo(vector, i);
            thread.start();
            listaDeThreads.add(thread);
        }

        //Espera o calcula de todas as threads
        for (int i = 0; i < listaDeThreads.size(); i++) {
            listaDeThreads.get(i).join();
        }

        //imprime a lista de numeros
        System.out.println("Quantidade de número primos: " + vector.size());
        System.out.println(" Números primos: " + vector);
    }
}
