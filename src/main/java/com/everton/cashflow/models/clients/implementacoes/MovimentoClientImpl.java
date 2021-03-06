package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.MovimentoClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.entidades.Produto;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MovimentoClientImpl implements MovimentoClient {

    private static MovimentoClientImpl movimentoClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<Movimento> conversorUtil;

    public MovimentoClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    public static MovimentoClient getInstance(){
        return Objects.nonNull(movimentoClient)
                ? movimentoClient
                : new MovimentoClientImpl();
    }

    @Override
    public List<Movimento> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO));
        return conversorUtil.converterJsonEmListaEntidade(json, Movimento.class);
    }

    @Override
    public Movimento buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
        return conversorUtil.converterJsonEmEntidade(json, Movimento.class);
    }



    @Override
    public boolean cadastrar(Movimento movimento) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Movimento movimento, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
    }

    @Override
    public List<Movimento> pesquisaPorNome(String nome) {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("nome", nome);
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO_PESQUISA), parametros);
        return conversorUtil.converterJsonEmListaEntidade(json, Movimento.class);
    }
}
