package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.implementacoes.ContaClientImpl;
import com.everton.cashflow.models.clients.implementacoes.ContasPagarClientImpl;
import com.everton.cashflow.models.clients.interfaces.ContasPagarClient;
import com.everton.cashflow.models.entidades.ContasPagar;

import java.util.List;
import java.util.Objects;

public class ContasPagarService {
    private static ContasPagarService contasPagarService;
    private ContasPagarClient contasPagarClient;

    public static ContasPagarService getInstance(){
        return Objects.nonNull(contasPagarService)
                ? contasPagarService
                : new ContasPagarService();
    }

    public ContasPagarService() {
        this.contasPagarClient = ContasPagarClientImpl.getInstance();
    }

    public List<ContasPagar> listarTodos() {
        return contasPagarClient.listarTodos();
    }

    public ContasPagar buscarPorId(Long id) {
        return contasPagarClient.buscarPorId(id);
    }

    public boolean cadastrar(ContasPagar movimento) {
      return contasPagarClient.cadastrar(movimento);
    }

    public boolean alterar(ContasPagar movimento, Long id) {
       return contasPagarService.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return contasPagarService.deletar(id);
    }
}
