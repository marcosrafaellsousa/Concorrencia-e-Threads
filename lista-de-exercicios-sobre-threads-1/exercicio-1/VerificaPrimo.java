import java.util.Vector;

public class VerificaPrimo extends Thread{

    private Vector vector;
    private int numero;

    public VerificaPrimo(Vector vector, int numero) {
        this.vector = vector;
        this.numero = numero;
    }

    @Override
    public void run() {
        if (verificaPrimo()) {
            vector.add(numero);
            System.out.println(numero);
        }
    }

    private boolean verificaPrimo(){
        if (numero % 2 == 0) { //tirando os números pares de cara
            return false;
        }
        for (int i = 3; i < numero; i+=2) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

}
