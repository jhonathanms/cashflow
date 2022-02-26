package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Movimento;

public interface MovimentoClient extends GenericClient<Movimento>{
    MovimentoClient getInstance();
}
