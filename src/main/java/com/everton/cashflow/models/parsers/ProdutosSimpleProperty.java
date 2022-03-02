package com.everton.cashflow.models.parsers;

import com.everton.cashflow.models.entidades.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;


public class ProdutosSimpleProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    private final SimpleLongProperty id;
    private final SimpleStringProperty nomeProduto;
    private final SimpleDoubleProperty valorProduto;
    private final SimpleIntegerProperty estoque;

    public ProdutosSimpleProperty(SimpleLongProperty id, SimpleStringProperty nomeProduto, SimpleDoubleProperty valorProduto, SimpleIntegerProperty estoque) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.estoque = estoque;
    }

    public ProdutosSimpleProperty(Long id, String nomeProduto, Double valorProduto, Integer estoque) {
        this.id = new SimpleLongProperty(id);
        this.nomeProduto = new SimpleStringProperty(nomeProduto);
        this.valorProduto = new SimpleDoubleProperty(valorProduto);
        this.estoque = new SimpleIntegerProperty(estoque);
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

    public String getNomeProduto() {
        return nomeProduto.get();
    }

    public SimpleStringProperty nomeProdutoProperty() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto.set(nomeProduto);
    }

    public double getValorProduto() {
        return valorProduto.get();
    }

    public SimpleDoubleProperty valorProdutoProperty() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto.set(valorProduto);
    }

    public int getEstoque() {
        return estoque.get();
    }

    public SimpleIntegerProperty estoqueProperty() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque.set(estoque);
    }

    public static ProdutosSimpleProperty converterParaSimpleProperty(Produto produto){
        return new ProdutosSimpleProperty(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getValorProduto(),
                produto.getEstoque()
        );
    }

    public static Produto converterParaEntidade(ProdutosSimpleProperty produto){
        return new Produto(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getValorProduto(),
                produto.getEstoque(),
                null
        );
    }
}
