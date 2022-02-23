package com.everton.cashflow.models.clients.interfaces;

public interface GenericClient<T> {
    T listarTodos();
    T buscarPorId(Long id);
    T cadastrar(T t);
    T alterar(T t, Long id);
    void deletar();
}
