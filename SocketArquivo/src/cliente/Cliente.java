package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {

        // Conecta com o servidor
        Socket servidorTexto = new Socket("localhost", 12345);

        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo a ser enviado");
        String nomeArquivo = leitor.nextLine();

        File arquivo = new File (nomeArquivo);
        long tamanhoArquivo = arquivo.length();


        PrintStream enviaTexto = new PrintStream(servidorTexto.getOutputStream());
        enviaTexto.println(arquivo.getName());
        enviaTexto.println(tamanhoArquivo + "");

        servidorTexto.close();

        //Abrir o arquivo no formato binário
        BufferedInputStream arquivoBinario = new BufferedInputStream(new FileInputStream(arquivo));


        // Eviar arquivo pela conexão
        Socket servidorRecebeArquivo = new Socket("localhost", 12346);
        BufferedOutputStream canalDeEnvio = new BufferedOutputStream(servidorRecebeArquivo.getOutputStream());

        long bytesEnviados = 0;
        while (bytesEnviados + 256 < tamanhoArquivo) {
            //int fim = (int)(tamanhoArquivo - bytesEnviados);
            byte dados[] = new byte[256];
            arquivoBinario.read(dados,0,256);
            canalDeEnvio.write(dados,0,256);
            bytesEnviados += 256;
        }

        // Quando o tamanho do arquivo não é multiplo de 256
        if (bytesEnviados < tamanhoArquivo) {
            int fim = (int)(tamanhoArquivo - bytesEnviados);
            byte[] dados = new byte[fim];
            arquivoBinario.read(dados,0,fim);
            canalDeEnvio.write(dados,0,fim);
        }

        canalDeEnvio.close();
        servidorRecebeArquivo.close();
    }
}
