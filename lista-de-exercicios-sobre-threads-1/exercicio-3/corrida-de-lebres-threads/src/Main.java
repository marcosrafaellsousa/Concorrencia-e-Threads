public class Main {

    public static void main(String[] args) throws InterruptedException {
        Lebre lebre1 = new Lebre("lebre 1");
        Lebre lebre2 = new Lebre("lebre 2");
        Lebre lebre3 = new Lebre("lebre 3");
        lebre1.start();
        lebre2.start();
        lebre3.start();

        lebre1.join();
        lebre2.join();
        lebre3.join();

        Lebre vencedora = null;
        Lebre segundoLugar = null;
        Lebre terceiroLugar = null;

        System.out.println("Vencedora: " + vencedora.getNome() + " com" + vencedora.getTotalPulos());
        System.out.println("Segundo lugar: " + segundoLugar.getNome() + " com" + segundoLugar.getTotalPulos());
        System.out.println("Terceiro lugar: " + terceiroLugar.getNome() + " com" + terceiroLugar.getTotalPulos());
    }
}
