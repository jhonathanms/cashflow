package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.implementacoes.MovimentoClientImpl;
import com.everton.cashflow.models.clients.implementacoes.ProdutoClientImpl;
import com.everton.cashflow.models.clients.interfaces.MovimentoClient;
import com.everton.cashflow.models.entidades.Movimento;

import java.util.List;
import java.util.Objects;

public class MovimentoService{
    private static MovimentoService movimentoService;
    private MovimentoClient movimentoClient;

    public static MovimentoService getInstance(){
        return Objects.nonNull(movimentoService)
                ? movimentoService
                : new MovimentoService();
    }

    public MovimentoService() {
        this.movimentoClient = MovimentoClientImpl.getInstance();
    }

    public List<Movimento> listarTodos() {
        return movimentoClient.listarTodos();
    }

    public Movimento buscarPorId(Long id) {
        return movimentoClient.buscarPorId(id);
    }

    public boolean cadastrar(Movimento movimento) {
      return movimentoClient.cadastrar(movimento);
    }

    public boolean alterar(Movimento movimento, Long id) {
       return movimentoClient.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return movimentoClient.deletar(id);
    }
}
