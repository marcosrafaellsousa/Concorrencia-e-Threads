public class Conta {

    private int numero;
    private double valor;

    public Conta(int numero, double valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double sacar (double saque) {
        this.valor = this.valor - saque;
        return this.valor;
    }

    public double depositar (double deposito) {
        this.valor = this.valor + deposito;
        return this.valor;
    }
}
