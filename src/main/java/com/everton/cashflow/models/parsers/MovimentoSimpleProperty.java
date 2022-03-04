package com.everton.cashflow.models.parsers;

import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.entidades.Produto;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MovimentoSimpleProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    private SimpleLongProperty id;
    private SimpleDoubleProperty quantidadeProduto;
    private SimpleStringProperty dataVenda;
    private SimpleListProperty<Produto> produtos;
    private SimpleStringProperty cliente;
    private SimpleStringProperty modelo;
    private SimpleStringProperty natureza;
    private SimpleDoubleProperty valor;

    public MovimentoSimpleProperty() {
    }

    public MovimentoSimpleProperty(Long id, Double quantidadeProduto, String dataVenda, List<Produto> produtos, String cliente) {
        SimpleListProperty<Produto> produtoSimpleListProperty = new SimpleListProperty<>();
        produtoSimpleListProperty.addAll(produtos);
        this.id = new SimpleLongProperty(id);
        this.quantidadeProduto = new SimpleDoubleProperty(quantidadeProduto);
        this.dataVenda = new SimpleStringProperty(dataVenda);
        this.produtos = produtoSimpleListProperty;
        this.cliente = new SimpleStringProperty(cliente);
        this.modelo = new SimpleStringProperty("Doc");
        this.natureza = new SimpleStringProperty("Venda/Saída");
        this.valor = new SimpleDoubleProperty(10.00);
    }

    public double getValor() {
        return valor.get();
    }

    public SimpleDoubleProperty valorProperty() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public double getQuantidadeProduto() {
        return quantidadeProduto.get();
    }

    public SimpleDoubleProperty quantidadeProdutoProperty() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(double quantidadeProduto) {
        this.quantidadeProduto.set(quantidadeProduto);
    }

    public Object getDataVenda() {
        return dataVenda.get();
    }

    public SimpleStringProperty dataVendaProperty() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda.set(new SimpleDateFormat("dd-MM-yyyy").format(dataVenda));
    }

    public ObservableList<Produto> getProdutos() {
        return produtos.get();
    }

    public SimpleListProperty<Produto> produtosProperty() {
        return produtos;
    }

    public void setProdutos(ObservableList<Produto> produtos) {
        this.produtos.set(produtos);
    }

    public String getCliente() {
        return cliente.get();
    }

    public SimpleStringProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public String getModelo() {
        return modelo.get();
    }

    public SimpleStringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getNatureza() {
        return natureza.get();
    }

    public SimpleStringProperty naturezaProperty() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza.set(natureza);
    }

    public static MovimentoSimpleProperty converterParaSimpleProperty(Movimento movimento) {
        return new MovimentoSimpleProperty(
                movimento.getId(),
                movimento.getQuantidadeProduto(),
                movimento.getDataVenda(),
                movimento.getProdutos(),
                movimento.getClientes().getNomeCliente()
        );
    }

    public static Movimento converterParaEntidade(MovimentoSimpleProperty movimento) {
        Cliente cliente = new Cliente();
        cliente.setNomeCliente(movimento.getCliente());
        return new Movimento(
                movimento.getId(),
                movimento.getQuantidadeProduto(),
                (Date) movimento.getDataVenda(),
                movimento.getProdutos(),
                cliente
        );
    }
}
