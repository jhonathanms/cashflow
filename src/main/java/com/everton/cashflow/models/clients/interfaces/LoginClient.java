package com.everton.cashflow.models.clients.interfaces;

import com.everton.cashflow.models.entidades.Usuario;

import java.io.IOException;

public interface LoginClient{
    boolean autenticacao(Usuario usuario) throws IOException;
    boolean testarConexao(String urlBase) throws IOException;
}
