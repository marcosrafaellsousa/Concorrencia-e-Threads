public class Consumidor implements Runnable {
    private Deposito deposito;

    public Consumidor(Deposito deposito) {
        this.deposito = deposito;
    }

    private void comprar() {
        while (true) {
            deposito.retirar();
        }
    }

    @Override
    public void run() {
        comprar();
    }

}
