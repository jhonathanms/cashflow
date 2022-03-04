package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Movimento;

import java.util.List;

public interface MovimentoClient extends GenericClient<Movimento>{
    List<Movimento> pesquisaPorNome(String nome);
}
