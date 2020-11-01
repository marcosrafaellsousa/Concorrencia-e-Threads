public class Deposito {
    private final int indiceMax = 9;
    private int[] arrayDeCaixas = new int[20];
    private int itens = -1;


    public synchronized int retirar() {
        while (this.itens == -1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        this.notify();
        this.itens--;
        System.out.println("Caixa retirada: Sobram "+(this.itens+1)+" caixas");
        return arrayDeCaixas[this.itens + 1];
    }

    public synchronized void colocar(int caixa) {
        while (this.itens == indiceMax) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.itens++;
        System.out.println("Caixa armazenada: Passaram a ser "+(this.itens+1)+" caixas");
        arrayDeCaixas[this.itens] = caixa;
        this.notify();

    }

    public int getQuantidade() {
        return this.itens;
    }

}