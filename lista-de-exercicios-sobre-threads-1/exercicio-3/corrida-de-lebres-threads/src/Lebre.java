import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

public class Lebre extends Thread {

    private String nome;
    private int distanciaUltimoPulo;
    private int distanciaTotal = 0;
    private int totalPulos;
    private Timestamp horaDaVitoria;

    public Lebre(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        while (this.distanciaTotal < 20) {
            pula();
            yield();
            if (this.distanciaTotal >= 20) {
                horaDaVitoria = new Timestamp(System.currentTimeMillis());
//                System.out.println("ganhei");
            }
        }
    }

    private void pula() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
        this.distanciaUltimoPulo = randomNum;
        this.distanciaTotal += randomNum;
        System.out.println("Eu, " + nome + " pulei: " + randomNum + " metros");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDistanciaUltimoPulo(int distanciaUltimoPulo) {
        this.distanciaUltimoPulo = distanciaUltimoPulo;
    }

    public Timestamp getHoraDaVitoria() {
        return horaDaVitoria;
    }

    public void setHoraDaVitoria(Timestamp horaDaVitoria) {
        this.horaDaVitoria = horaDaVitoria;
    }

    public int getDistanciaUltimoPulo() {
        return distanciaUltimoPulo;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public int getTotalPulos() {
        return totalPulos;
    }

    public void setTotalPulos(int totalPulos) {
        this.totalPulos = totalPulos;
    }

}
