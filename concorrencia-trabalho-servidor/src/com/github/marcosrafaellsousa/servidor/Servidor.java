package com.github.marcosrafaellsousa.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    private static final int numeroMaxDeThreads = 10;
    public static void main(String args[]) throws IOException {

        ArrayList cardapio = popularProdutos(new ArrayList<Produto>());
        ServerSocket servidor = new ServerSocket(12345);

        // Instanciando threads
        ExecutorService executorService = Executors.newFixedThreadPool(numeroMaxDeThreads);
        while (true) {
            Socket cliente = servidor.accept();
            //System.out.println("Cliente conectado do IP: " + cliente.getInetAddress().getHostAddress());
            executorService.submit(new ThreadServidor(cardapio, cliente));
        }
    }

    private static ArrayList<Produto> popularProdutos(ArrayList<Produto> produtos) {
        produtos.add(new Produto(1, "Sushi", 70.00));
        produtos.add(new Produto(2, "Udon", 45.00));
        produtos.add(new Produto(3, "Tofu", 32.00));
        produtos.add(new Produto(4, "Tempura", 40.00));
        produtos.add(new Produto(5, "Yakitori", 12.00));
        produtos.add(new Produto(6, "Sashimi", 55.00));
        produtos.add(new Produto(7, "Ramen", 64.00));
        produtos.add(new Produto(8, "Donburi", 67.00));
        produtos.add(new Produto(9, "Natto", 24.00));
        produtos.add(new Produto(10, "Oden", 41.00));
        produtos.add(new Produto(11, "Tamagoyaki", 12.00));
        produtos.add(new Produto(12, "Soba", 49.00));
        produtos.add(new Produto(13, "Tonkatsu", 33.00));
        produtos.add(new Produto(14, "Kashipan", 14.00));
        produtos.add(new Produto(15, "Sukiyaki", 67.00));
        produtos.add(new Produto(16, "Miso Soup", 45.00));
        produtos.add(new Produto(17, "Okonomiyaki", 33.00));
        produtos.add(new Produto(18, "Mentaiko", 76.00));
        produtos.add(new Produto(19, "Nikujaga", 50.00));
        produtos.add(new Produto(20, "Curry Rice", 25.00));
        produtos.add(new Produto(21, "Mapo Tofu Super Picante Que Ninguém Ousa Pedir", 9999.00));
        return produtos;
    }
}