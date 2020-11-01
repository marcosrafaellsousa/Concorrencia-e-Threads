public class BuscaSubArray extends Thread {

    private int[] A;
    private int x;
    private int indice;
    private int posInicial;

    public BuscaSubArray(int[] a, int x, int indice, int posInicial) {
        A = a;
        this.x = x;
        this.indice = indice;
    }

    @Override
    public void run() {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
//                System.out.println("achei!");
                indice = posInicial + i;
            }
        }
    }

    public int getIndice() {
        return indice;
    }
}
