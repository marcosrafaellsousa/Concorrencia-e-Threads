package com.github.marcosrafaellsousa.servidor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ThreadServidor implements Runnable{


    private Map<Integer, String> frases;
    private Socket cliente;

    public ThreadServidor(Map<Integer, String> frases, Socket cliente) {
        this.frases = frases;
        this.cliente = cliente;
    }


    @Override
    public void run() {
        Scanner mensagemRecebida = null;
        try {
            mensagemRecebida = new Scanner(cliente.getInputStream());
            if (mensagemRecebida.hasNextLine() && mensagemRecebida.nextLine().equals("Me de uma frase")) {
                OutputStream respostaEnviada = cliente.getOutputStream();
                PrintWriter mensagem = new PrintWriter(respostaEnviada, true);
                mensagem.println(frases.get(new Random().nextInt(159) + 1));
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
