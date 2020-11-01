package com.github.marcosrafaellsousa.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket servidor = new ServerSocket (12345);
        Socket conexaoCliente = servidor.accept();
        System.out.println("Cliente se conecotou ao servidor");
        conexaoCliente.close();


    }
}
