package com.everton.cashflow.models.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movimento implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double quantidadeProduto;
    private LocalDate dataVenda;
    private List<Produto> produtos = new ArrayList<>();
    private Cliente clientes;

    public Movimento() {
    }

    public Movimento(Long id, Double quantidadeProduto, LocalDate dataVenda, List<Produto> produtos, Cliente clientes) {
        this.id = id;
        this.quantidadeProduto = quantidadeProduto;
        this.dataVenda = dataVenda;
        this.produtos = produtos;
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }
}
