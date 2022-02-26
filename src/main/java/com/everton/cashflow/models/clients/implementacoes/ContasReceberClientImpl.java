package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.ContasReceberClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.ContasReceber;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ContasReceberClientImpl implements ContasReceberClient {

    private static ContasReceberClientImpl contasReceberClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<ContasReceber> conversorUtil;

    @Override
    public ContasReceberClient getInstance(){
        return Objects.nonNull(contasReceberClient)
                ? contasReceberClient
                : new ContasReceberClientImpl();
    }

    public ContasReceberClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    @Override
    public List<ContasReceber> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO));
        return conversorUtil.converterJsonEmListaEntidade(json);
    }

    @Override
    public ContasReceber buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
        return conversorUtil.converterJsonEmEntidade(json, ContasReceber.class);
    }

    @Override
    public boolean cadastrar(ContasReceber movimento) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(ContasReceber movimento, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
    }
}
