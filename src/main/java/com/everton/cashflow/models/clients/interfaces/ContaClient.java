package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Conta;

public interface ContaClient extends GenericClient<Conta>{
    ContaClient getInstance();
}
