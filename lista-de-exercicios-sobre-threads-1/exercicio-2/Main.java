import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int[] vetor = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        int x = 20;
        int numThreads = 2;
        System.out.println(parallelSearch(x, vetor, numThreads));

    }

    public static int parallelSearch(int x, int[] A, int numThreads) throws InterruptedException {
        ArrayList<BuscaSubArray> listaDeThreads = new ArrayList<>();
        int indice = -1;

        //Determina o tamanho dos subarrays
        int tamanhoSubArray = A.length / numThreads;
        int resto = A.length % numThreads;

        //Instancia as Threads
        for (int i = 0; (i + resto) < A.length; i += tamanhoSubArray) {
            if (i == (numThreads - 1)) { //Caso o tamanho do array não seja divisível pelo número de threads
                BuscaSubArray thread = new BuscaSubArray(Arrays.copyOfRange
                        (A, i, (i + tamanhoSubArray + resto)), x, indice, i);
//                System.out.println("array posição: " + i + " até posição: " + (i + tamanhoSubArray));
                thread.start();
                listaDeThreads.add(thread);
            } else {
                BuscaSubArray thread = new BuscaSubArray(Arrays.copyOfRange
                        (A, (i), (i + tamanhoSubArray)), x, indice, i);
//                System.out.println("array posição: " + i + " até posição: " + (i + tamanhoSubArray));
                thread.start();
                listaDeThreads.add(thread);
            }
        }

        // Espera todas as threads finalizarem a busca
        for (int i = 0; i < listaDeThreads.size(); i++) {
            listaDeThreads.get(i).join();
        }
        // Procura o indice
        for (int i = 0; i < listaDeThreads.size(); i++) {
            if (listaDeThreads.get(i).getIndice() != -1)
                indice = listaDeThreads.get(i).getIndice();
        }
        return indice;
    }

}
