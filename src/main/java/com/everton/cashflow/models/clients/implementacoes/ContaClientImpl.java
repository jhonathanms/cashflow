package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.ContaClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.Conta;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ContaClientImpl implements ContaClient {

    private static ContaClientImpl contaClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<Conta> conversorUtil;

    @Override
    public ContaClient getInstance(){
        return Objects.nonNull(contaClient)
                ? contaClient
                : new ContaClientImpl();
    }

    public ContaClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    @Override
    public List<Conta> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO));
        return conversorUtil.converterJsonEmListaEntidade(json);
    }

    @Override
    public Conta buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
        return conversorUtil.converterJsonEmEntidade(json, Conta.class);
    }

    @Override
    public boolean cadastrar(Conta movimento) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Conta movimento, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_MOVIMENTO), id);
    }
}
