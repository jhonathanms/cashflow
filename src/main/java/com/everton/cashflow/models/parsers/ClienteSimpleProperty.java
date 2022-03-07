package com.everton.cashflow.models.parsers;

import com.everton.cashflow.models.entidades.Cliente;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;


public class ClienteSimpleProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    private final SimpleLongProperty id;
    private final SimpleStringProperty nomeCliente;

    public ClienteSimpleProperty(SimpleLongProperty id, SimpleStringProperty nomeCliente) {
        this.id = id;
        this.nomeCliente = nomeCliente;
    }

    public ClienteSimpleProperty(Long id, String nomeCliente) {
        this.id = new SimpleLongProperty(id);
        this.nomeCliente = new SimpleStringProperty(nomeCliente);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getNomeCliente() {
        return nomeCliente.get();
    }

    public SimpleStringProperty nomeClienteProperty() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente.set(nomeCliente);
    }

    public static ClienteSimpleProperty converterParaSimpleProperty(Cliente cliente){
        return new ClienteSimpleProperty(cliente.getId(), cliente.getNomeCliente());
    }

    public static Cliente converterParaEntidade(ClienteSimpleProperty clienteSimpleProperty){
        return new Cliente(clienteSimpleProperty.getId(), clienteSimpleProperty.getNomeCliente(), null);
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }

}
