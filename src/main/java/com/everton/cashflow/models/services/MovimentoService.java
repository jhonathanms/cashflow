package com.everton.cashflow.models.services;

import com.everton.cashflow.models.clients.implementacoes.MovimentoClientImpl;
import com.everton.cashflow.models.clients.interfaces.MovimentoClient;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.parsers.MovimentoSimpleProperty;
import javafx.collections.ObservableList;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MovimentoService{
    private static MovimentoService movimentoService;
    private MovimentoClient movimentoClient;

    public static MovimentoService getInstance(){
        return Objects.nonNull(movimentoService)
                ? movimentoService
                : new MovimentoService();
    }

    public MovimentoService() {
        this.movimentoClient = MovimentoClientImpl.getInstance();
    }

    public List<Movimento> listarTodos() {
        return movimentoClient.listarTodos();
    }

    public List<Movimento> pesquisaPorNome(String nome){
        return movimentoClient.pesquisaPorNome(nome);
    }

    public Movimento buscarPorId(Long id) {
        return movimentoClient.buscarPorId(id);
    }

    public boolean cadastrar(Movimento movimento) {
      return movimentoClient.cadastrar(movimento);
    }

    public boolean alterar(Movimento movimento, Long id) {
       return movimentoClient.alterar(movimento, id);
    }

    public boolean deletar(Long id) {
        return movimentoClient.deletar(id);
    }

    public String obterTotalMovimentos(ObservableList<MovimentoSimpleProperty> movimentos){
        return  NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(movimentos.stream()
                .mapToDouble(MovimentoSimpleProperty::getValor)
                .sum());
    }

    public int obterQuantidadeMovimentos(ObservableList<MovimentoSimpleProperty> movimentos){
        return movimentos.size();
    }

}
