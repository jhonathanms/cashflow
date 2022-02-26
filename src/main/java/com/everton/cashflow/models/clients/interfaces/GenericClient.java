package com.everton.cashflow.models.clients.interfaces;

import java.util.List;

public interface GenericClient<T> {
    List<T> listarTodos();
    T buscarPorId(Long id);
    boolean cadastrar(T t);
    boolean alterar(T t, Long id);
    boolean deletar(Long id);
}
