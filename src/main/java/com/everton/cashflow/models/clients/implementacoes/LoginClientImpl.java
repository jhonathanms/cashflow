package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.LoginClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import com.everton.cashflow.models.entidades.UsuarioDTO;
import com.everton.cashflow.util.ExtracaoDeDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class LoginClientImpl implements LoginClient {

    private static LoginClientImpl loginClient;
    private RequestResponse<UsuarioDTO> requisicao;

    public static LoginClient getInstance(){
        return Objects.nonNull(loginClient)
                ? loginClient
                : new LoginClientImpl();
    }

    public LoginClientImpl() {
        this.requisicao = new RequestResponse<>();
    }

    @Override
    public boolean autenticacao(Usuario usuario){
        Properties props = ExtracaoDeDados.obterPropriedades();
        assert props != null;
        String urlBase = props.getProperty(Constantes.PROP_URL_BASE);
        String jsonUsuario = converterEntidadeEmJson( UsuarioDTO.converterParaDto(usuario));

        try {
           return requisicao.post(urlBase.concat(Constantes.ENDPOINT_LOGIN), jsonUsuario);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean testarConexao(String urlBase) throws IOException {
        return requisicao.testeConexao(urlBase);
    }

    public String converterEntidadeEmJson(UsuarioDTO entidade){
        try {
            return new ObjectMapper().writer().writeValueAsString(entidade);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
