package com.github.marcosrafaellsousa.cliente;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket cliente = new Socket("127.0.0.1", 12345);

        //Recebendo o cardápio
        InputStream canalDeRecebimento = cliente.getInputStream();
        DataInputStream recebedorDeCardapio = new DataInputStream(canalDeRecebimento);
        Gson gson = new Gson();
        String cardapioJson = recebedorDeCardapio.readUTF();
        Produto[] arrayProdutosDoJson = gson.fromJson(cardapioJson, Produto[].class);
        ArrayList<Produto> cardapio = new ArrayList<>(Arrays.asList(arrayProdutosDoJson));

        //Imprimindo o cardápio
        for (Produto produto : cardapio) {
            System.out.println(produto);
        }
        System.out.println();

        //Fazendo um pedido
        System.out.println("Bem Vindo a nosso Restaurante de Comida Japonesa, escolha um prato do cardápio");
        Scanner leitorDoTeclado = new Scanner(System.in);
        int pedido = 0;
        while (true) {
            try {
                pedido = leitorDoTeclado.nextInt();
                if (pedido < 1 || pedido > cardapio.size())
                    throw new InputMismatchException();
                leitorDoTeclado.nextLine();
                break;
            } catch (InputMismatchException e) {
                leitorDoTeclado.nextLine();
                System.out.println("Você precisa digitar o número de um dos pratos do cardápio");
            }
        }

        //Enviando o pedido
        OutputStream canalDeEnvio = cliente.getOutputStream();
        DataOutputStream enviadorDePedido = new DataOutputStream(canalDeEnvio);
        enviadorDePedido.writeInt(pedido);

        //Recebendo a conta
        DataInputStream recebedorDeConta = new DataInputStream(canalDeRecebimento);
        String contaJson = recebedorDeConta.readUTF();
        Produto conta = gson.fromJson(contaJson, Produto.class);

        //Imprimindo a conta
        System.out.println("Obrigado por pedir em nosso restaurante, sua conta: ");
        System.out.println("1x " + conta.getNome() + " - " + "R$: " + conta.getPreco());
        cliente.close();
    }
}