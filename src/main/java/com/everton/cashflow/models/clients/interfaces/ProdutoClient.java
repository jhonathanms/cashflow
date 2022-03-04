package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.entidades.Produto;

import java.util.List;

public interface ProdutoClient extends GenericClient<Produto>{
    List<Produto> pesquisaPorNome(String nome);
}
