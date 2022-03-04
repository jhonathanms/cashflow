package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.ContasPagarClient;
import com.everton.cashflow.models.clients.interfaces.MovimentoClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.models.entidades.Conta;
import com.everton.cashflow.models.entidades.ContasPagar;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ContasPagarClientImpl implements ContasPagarClient {

    private static ContasPagarClientImpl contasPagarClient;
    private RestTemplate<UsuarioDTO> restTemplate;
    private String urlBase;
    private ConversorUtil<ContasPagar> conversorUtil;

    public static ContasPagarClient getInstance(){
        return Objects.nonNull(contasPagarClient)
                ? contasPagarClient
                : new ContasPagarClientImpl();
    }

    public ContasPagarClientImpl() {
        this.urlBase = ExtracaoDeDados.obterURLBase();
        this.restTemplate = RestTemplate.getInstance();
        this.conversorUtil = ConversorUtil.getInstance();
    }

    @Override
    public List<ContasPagar> listarTodos() {
        String json = restTemplate.get(urlBase.concat(Constantes.ENDPOINT_FINANCEIRO_A_PAGAR));
        return conversorUtil.converterJsonEmListaEntidade(json, ContasPagar.class);
    }

    @Override
    public ContasPagar buscarPorId(Long id) {
        String json = restTemplate.getById(urlBase.concat(Constantes.ENDPOINT_FINANCEIRO_A_PAGAR), id);
        return conversorUtil.converterJsonEmEntidade(json, ContasPagar.class);
    }

    @Override
    public boolean cadastrar(ContasPagar movimento) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        try {
            return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_FINANCEIRO_A_PAGAR), json);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(ContasPagar movimento, Long id) {
        String json = conversorUtil.converterEntidadeEmJson(movimento);
        return restTemplate.put(urlBase.concat(Constantes.ENDPOINT_FINANCEIRO_A_PAGAR), id, json);
    }

    @Override
    public boolean deletar(Long id) {
        return restTemplate.delete(urlBase.concat(Constantes.ENDPOINT_FINANCEIRO_A_PAGAR), id);
    }
}
