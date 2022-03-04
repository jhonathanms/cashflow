package com.everton.cashflow.models.parsers;

import com.everton.cashflow.models.entidades.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class ProdutosSimpleProperty implements Serializable {
    private static final long serialVersionUID = 1L;

    private final SimpleLongProperty id;
    private final SimpleStringProperty nomeProduto;
    private final SimpleStringProperty valorProduto;
    private final SimpleIntegerProperty estoque;
    private String modelo = "Doc";
    private String Natureza = "Venda/Saída";

    public ProdutosSimpleProperty(SimpleLongProperty id, SimpleStringProperty nomeProduto, SimpleDoubleProperty valorProduto, SimpleIntegerProperty estoque) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.valorProduto = new SimpleStringProperty(
                NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                        .format(valorProduto.get()));
        this.estoque = estoque;
    }

    public ProdutosSimpleProperty(Long id, String nomeProduto, Double valorProduto, Integer estoque) {
        this.id = new SimpleLongProperty(id);
        this.nomeProduto = new SimpleStringProperty(nomeProduto);
        this.valorProduto = new SimpleStringProperty(
                NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                        .format(valorProduto));
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

    public String getValorProduto() {
        return valorProduto.get();
    }

    public SimpleStringProperty valorProdutoProperty() {
        return valorProduto;
    }

    public void setValorProduto(String valorProduto) {
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNatureza() {
        return Natureza;
    }

    public void setNatureza(String natureza) {
        Natureza = natureza;
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
        try {
            return new Produto(
                    produto.getId(),
                    produto.getNomeProduto(),
                    (Double) NumberFormat.getCurrencyInstance(new Locale(Locale.US.getLanguage(), Locale.US.getCountry()))
                            .parse(produto.getValorProduto()),
                    produto.getEstoque(),
                    null
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
