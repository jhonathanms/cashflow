package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.ClienteClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ClienteClientImpl implements ClienteClient {

    private static ClienteClientImpl clienteClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<Cliente> conversorUtil;

    public static ClienteClient getInstance(){
        return Objects.nonNull(clienteClient)
                ? clienteClient
                : new ClienteClientImpl();
    }

    public ClienteClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    @Override
    public List<Cliente> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_CLIENTE));
        return conversorUtil.converterJsonEmListaEntidade(json, Cliente.class);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_CLIENTE), id);
        return conversorUtil.converterJsonEmEntidade(json, Cliente.class);
    }

    @Override
    public List<Cliente> pesquisaPorNome(String nome) {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("nome", nome);
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_CLIENTE_PESQUISA), parametros);
        return conversorUtil.converterJsonEmListaEntidade(json, Cliente.class);
    }

    @Override
    public boolean cadastrar(Cliente movimento) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_CLIENTE), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Cliente movimento, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_CLIENTE), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_CLIENTE), id);
    }
}
