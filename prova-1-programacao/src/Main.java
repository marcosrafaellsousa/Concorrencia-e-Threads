public class Main {
    public static void main(String[] args) {

        Deposito dep = new Deposito();

        Thread p = new Thread(new Produtor(dep));
        Thread c = new Thread(new Consumidor(dep));

        p.start();
        c.start();

        System.out.println("Execução do main terminada!");
    }
}