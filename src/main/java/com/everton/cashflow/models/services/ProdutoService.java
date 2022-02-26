package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.interfaces.ProdutoClient;
import com.everton.cashflow.models.entidades.Produto;

import java.util.List;
import java.util.Objects;

public class ProdutoService {
    private static ProdutoService produtoService;
    private ProdutoClient produtoClient;

    public static ProdutoService getInstance(){
        return Objects.nonNull(produtoService)
                ? produtoService
                : new ProdutoService();
    }

    public List<Produto> listarTodos() {
        return produtoClient.listarTodos();
    }

    public Produto buscarPorId(Long id) {
        return produtoClient.buscarPorId(id);
    }

    public boolean cadastrar(Produto movimento) {
        return produtoClient.cadastrar(movimento);
    }

    public boolean alterar(Produto movimento, Long id) {
        return produtoService.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return produtoService.deletar(id);
    }
}
