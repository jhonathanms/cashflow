package com.everton.cashflow.models.entidades;

import java.io.Serializable;

public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;;
	private Usuario usuario;

	public Conta() {
	}

	public Conta(Long id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
