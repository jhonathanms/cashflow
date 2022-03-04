package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Cliente;

import java.util.List;

public interface ClienteClient extends GenericClient<Cliente>{
    List<Cliente> pesquisaPorNome(String nome);
}
