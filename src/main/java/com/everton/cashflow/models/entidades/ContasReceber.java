package com.everton.cashflow.models.entidades;

import java.io.Serializable;
import java.time.LocalDate;

public class ContasReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Movimento movimento;
	private LocalDate dataEmissao = LocalDate.now();
	private LocalDate dataRecebimento = LocalDate.now();
	private Double valorReceber;

	public ContasReceber(Long id, Movimento movimento, LocalDate dataEmissao, LocalDate dataRecebimento, Double valorReceber) {
		this.id = id;
		this.movimento = movimento;
		this.dataEmissao = dataEmissao;
		this.dataRecebimento = dataRecebimento;
		this.valorReceber = valorReceber;
	}

	public ContasReceber() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movimento getMovimento() {
		return movimento;
	}

	public void setMovimento(Movimento movimento) {
		this.movimento = movimento;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Double getValorReceber() {
		return valorReceber;
	}

	public void setValorReceber(Double valorReceber) {
		this.valorReceber = valorReceber;
	}
}

