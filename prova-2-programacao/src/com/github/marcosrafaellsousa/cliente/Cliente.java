package com.github.marcosrafaellsousa.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 12345);
        OutputStream mensagemEnviada = cliente.getOutputStream();
        PrintWriter mensagem = new PrintWriter(mensagemEnviada, true);
        mensagem.println("Me de uma frase");

        Scanner respostaRecebida = new Scanner(cliente.getInputStream());
        while (respostaRecebida.hasNextLine()) {
            System.out.println(respostaRecebida.nextLine());
        }

        cliente.close();
        System.out.println("Conexão finalizada");
    }
}