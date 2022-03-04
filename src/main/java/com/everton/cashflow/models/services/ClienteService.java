package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.implementacoes.ClienteClientImpl;
import com.everton.cashflow.models.clients.interfaces.ClienteClient;
import com.everton.cashflow.models.entidades.Cliente;

import java.util.List;
import java.util.Objects;

public class ClienteService {
    private static ClienteService clienteService;
    private ClienteClient clienteClient;

    public static ClienteService getInstance(){
        return Objects.nonNull(clienteService)
                ? clienteService
                : new ClienteService();
    }

    public ClienteService() {
        this.clienteClient = ClienteClientImpl.getInstance();
    }

    public List<Cliente> listarTodos() {
        return clienteClient.listarTodos();
    }

    public List<Cliente> pesquisaPorNome(String nome){
        return clienteClient.pesquisaPorNome(nome);
    }

    public Cliente buscarPorId(Long id) {
        return clienteClient.buscarPorId(id);
    }

    public boolean cadastrar(Cliente movimento) {
      return clienteClient.cadastrar(movimento);
    }

    public boolean alterar(Cliente movimento, Long id) {
       return clienteClient.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return clienteClient.deletar(id);
    }
}
