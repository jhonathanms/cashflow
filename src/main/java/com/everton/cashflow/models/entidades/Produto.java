package com.everton.cashflow.models.entidades;


import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeProduto;
    private Double valorProduto;
    private Integer estoque;
    private Movimento movimento;

    public Produto() {
    }

    public Produto(Long id, String nomeProduto, Double valorProduto, Integer estoque, Movimento movimento) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.estoque = estoque;
        this.movimento = movimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

    @Override
    public String toString() {
        return nomeProduto;
    }
}
