package com.github.marcosrafaellsousa.servidor;

import java.io.Serializable;

public class Produto implements Serializable {

    private int numero;
    private String nome;
    private double preco;

    public Produto(int numero, String nome, double preco) {
        this.numero = numero;
        this.nome = nome;
        this.preco = preco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
