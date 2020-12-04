package com.github.marcosrafaellsousa.servidor;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServidor implements Runnable {


    private ArrayList<Produto> cardapio;
    private Socket cliente;

    public ThreadServidor(ArrayList<Produto> produtos, Socket cliente) {
        this.cardapio = produtos;
        this.cliente = cliente;
    }


    @Override
    public void run() {
        try {
            // Enviando cardápio
            OutputStream canalDeEnvio = cliente.getOutputStream();
            DataOutputStream enviadorDeCardapio = new DataOutputStream(canalDeEnvio);
            Gson gson = new Gson();
            String cardapioJson = gson.toJson(cardapio);
            enviadorDeCardapio.writeUTF(cardapioJson);

            //Recebendo o pedido
            InputStream canalDeRecebimento = cliente.getInputStream();
            DataInputStream recebedorDePedido = new DataInputStream(canalDeRecebimento);
            int pedido = recebedorDePedido.readInt();
            System.out.println("Cliente: " + cliente.getInetAddress().getHostAddress() + " Fez um pedido com o prato "
                    + pedido);

            // Enviando a conta
            Produto conta = cardapio.get(pedido - 1);
            DataOutputStream enviadorDeConta = new DataOutputStream(canalDeEnvio);
            String contaJson = gson.toJson(conta);
            enviadorDeConta.writeUTF(contaJson);

            System.out.println("Conta enviada para o cliente: " + cliente.getInetAddress().getHostAddress());
            cliente.close();
        } catch (IOException e) {
            System.out.println("Erro ao se comunicar com o cliente:");
            e.printStackTrace();
        }
    }
}
