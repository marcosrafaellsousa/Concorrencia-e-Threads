public class Produtor implements Runnable {
    private Deposito deposito;

    public Produtor(Deposito deposito) {
        this.deposito = deposito;
    }

    private void produzir() {
        int numCaixa = 1;
        while(true) {
            deposito.colocar(numCaixa);
            numCaixa++;
        }
    }

    @Override
    public void run() {
        produzir();
    }
}
