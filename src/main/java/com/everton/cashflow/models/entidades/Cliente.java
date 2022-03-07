package com.everton.cashflow.models.entidades;

import java.io.Serializable;
import java.util.List;


public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeCliente;
    private List<Movimento> movimento;

    public Cliente() {
    }

    public Cliente(Long id, String nomeCliente, List<Movimento> movimento) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.movimento = movimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Movimento> getMovimento() {
        return movimento;
    }

    public void setMovimento(List<Movimento> movimento) {
        this.movimento = movimento;
    }

    @Override
    public String toString() {
        return nomeCliente;
    }
}
