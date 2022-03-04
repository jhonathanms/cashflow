package com.everton.cashflow.models.enums;

import java.util.Arrays;

public enum FormaDePagamento {
	
	DINHEIRO(1),
	DEBITO(2),
	CREDITO(3),
	CHEQUE(4),
	BOLETO(5);
	
	private int valor;

	FormaDePagamento(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}

	public FormaDePagamento getFormaDePagamento(int valor){
		return Arrays.stream(FormaDePagamento.values())
				.filter( formaDePagamento -> valor == formaDePagamento.getValor())
				.findFirst()
				.orElse(null);
	}

}
