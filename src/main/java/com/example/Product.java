package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String raca;
    private double valor;
    private int quantidade;
    protected Product() {}
    public Product(String raca, 
    double valor, int quantidade) {
        this.raca = raca;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    /**
     * @return the raca
     */
    public String getRaca() {
        return this.raca;
    }
    /**
     * @param raca the raca to set
     */
    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getValor() {
        return this.valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}