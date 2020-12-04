package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket servidorTexto = new ServerSocket(12345);

        Socket clienteTexto = servidorTexto.accept();

        Scanner recebeTexto = new Scanner(clienteTexto.getInputStream());

        String nomeArquivoRecebido = recebeTexto.nextLine();

        long tamanhoArquivo = Long.parseLong(recebeTexto.nextLine());

        clienteTexto.close();
        recebeTexto.close();

        System.out.println("nome do arquivo: " + nomeArquivoRecebido + ", Tamanho: " + tamanhoArquivo);

        servidorTexto.close();

        //Transferencia do arquivo

        ServerSocket servidorArquivo = new ServerSocket(12346);
        Socket clienteArquivo = servidorArquivo.accept();

        String nomeArquivoServidor = "src/recebidos/" + nomeArquivoRecebido;
        BufferedOutputStream arquivo = new BufferedOutputStream(new FileOutputStream(nomeArquivoServidor));


        long bytesRecebidos = 0;
        BufferedInputStream canalDeRecebimento = new BufferedInputStream(clienteArquivo.getInputStream());


        while (bytesRecebidos + 256 < tamanhoArquivo) {
            byte dados[] = new byte[256];
            canalDeRecebimento.read(dados,0,256);
            arquivo.write(dados,0,256);
            bytesRecebidos += 256;
        }

        // Quando o tamanho do arquivo não é multiplo de 256
        if (bytesRecebidos < tamanhoArquivo) {
            int quantBytesRestantes = (int)(tamanhoArquivo - bytesRecebidos);
            byte bytesRestantes[] = new byte[quantBytesRestantes];
            canalDeRecebimento.read(bytesRestantes,0,quantBytesRestantes);
            arquivo.write(bytesRestantes,0,quantBytesRestantes);
        }

        arquivo.close();
        canalDeRecebimento.close();
        clienteArquivo.close();
        servidorArquivo.close();

    }

}
