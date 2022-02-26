package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.ProdutoClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.entidades.Produto;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ProdutoClientImpl implements ProdutoClient {

    private static ProdutoClientImpl produtoClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<Produto> conversorUtil;

    public static ProdutoClient getInstance(){
        return Objects.nonNull(produtoClient)
                ? produtoClient
                : new ProdutoClientImpl();
    }

    public ProdutoClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    @Override
    public List<Produto> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_PRODUTO));
        return conversorUtil.converterJsonEmListaEntidade(json, Produto.class);
    }

    @Override
    public Produto buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_PRODUTO), id);
        return conversorUtil.converterJsonEmEntidade(json, Produto.class);
    }

    @Override
    public boolean cadastrar(Produto produto) {
        String json = conversorUtil.converterEntidadeEmJson(produto);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_PRODUTO), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Produto produto, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(produto);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_PRODUTO), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_PRODUTO), id);
    }
}
