package com.everton.cashflow.models.entidades;

import com.everton.cashflow.models.enums.FormaDePagamento;

import java.io.Serializable;
import java.time.LocalDate;


public class ContasPagar implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate dataVenciemnto;
	private LocalDate dataEmissao;
	private LocalDate dataPagamento;
	private String descricao;
	private Double valorPagar;
	private FormaDePagamento formaDePagamento;

	public ContasPagar() {
	}

	public ContasPagar(Long id, LocalDate dataVenciemnto, LocalDate dataEmissao, LocalDate dataPagamento, String descricao, Double valorPagar, FormaDePagamento formaDePagamento) {
		this.id = id;
		this.dataVenciemnto = dataVenciemnto;
		this.dataEmissao = dataEmissao;
		this.dataPagamento = dataPagamento;
		this.descricao = descricao;
		this.valorPagar = valorPagar;
		this.formaDePagamento = formaDePagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVenciemnto() {
		return dataVenciemnto;
	}

	public void setDataVenciemnto(LocalDate dataVenciemnto) {
		this.dataVenciemnto = dataVenciemnto;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(Double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
}
