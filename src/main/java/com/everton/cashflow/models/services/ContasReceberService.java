package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.implementacoes.ContaClientImpl;
import com.everton.cashflow.models.clients.implementacoes.ContasReceberClientImpl;
import com.everton.cashflow.models.clients.interfaces.ContasReceberClient;
import com.everton.cashflow.models.entidades.ContasReceber;

import java.util.List;
import java.util.Objects;

public class ContasReceberService {
    private static ContasReceberService contasReceberService;
    private ContasReceberClient contasReceberClient;

    public static ContasReceberService getInstance(){
        return Objects.nonNull(contasReceberService)
                ? contasReceberService
                : new ContasReceberService();
    }
    public ContasReceberService() {
        this.contasReceberClient = ContasReceberClientImpl.getInstance();
    }

    public List<ContasReceber> listarTodos() {
        return contasReceberClient.listarTodos();
    }

    public ContasReceber buscarPorId(Long id) {
        return contasReceberClient.buscarPorId(id);
    }

    public boolean cadastrar(ContasReceber movimento) {
      return contasReceberClient.cadastrar(movimento);
    }

    public boolean alterar(ContasReceber movimento, Long id) {
       return contasReceberService.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return contasReceberService.deletar(id);
    }
}
