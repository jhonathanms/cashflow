package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.interfaces.ContaClient;
import com.everton.cashflow.models.entidades.Conta;

import java.util.List;
import java.util.Objects;

public class ContaService {
    private static ContaService contaService;
    private ContaClient contaClient;

    public static ContaService getInstance(){
        return Objects.nonNull(contaService)
                ? contaService
                : new ContaService();
    }

    public List<Conta> listarTodos() {
        return contaClient.listarTodos();
    }

    public Conta buscarPorId(Long id) {
        return contaClient.buscarPorId(id);
    }

    public boolean cadastrar(Conta movimento) {
      return contaClient.cadastrar(movimento);
    }

    public boolean alterar(Conta movimento, Long id) {
       return contaService.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return contaService.deletar(id);
    }
}
