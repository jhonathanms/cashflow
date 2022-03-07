package com.everton.cashflow.models.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movimento implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double quantidadeProduto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataVenda;
    private List<Produto> produtos = new ArrayList<>();
    private Cliente clientes;

    public Movimento() {
    }

    public Movimento(Long id, Double quantidadeProduto, Date dataVenda, List<Produto> produtos, Cliente clientes) {
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

    public String getDataVenda() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dataVenda);
    }

    public void setDataVenda(Date dataVenda) {
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
