package com.everton.cashflow.models.entidades;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private String login;
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static UsuarioDTO converterParaDto(Usuario usuario){
        return new UsuarioDTO(usuario.getLogin(), usuario.getSenha());
    }
}
