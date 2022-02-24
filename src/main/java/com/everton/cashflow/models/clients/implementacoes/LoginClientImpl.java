package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.LoginClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class LoginClientImpl implements LoginClient {

    private static LoginClientImpl loginClient;
    private RequestResponse<Usuario> requisicao;

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
        Map<String, String> parametros = new HashMap<>();
        parametros.put("login", usuario.getLogin());
        parametros.put("senha", usuario.getSenha());

        Properties props = ExtracaoDeDados.obterPropriedades();
        assert props != null;
        String urlBase = props.getProperty(Constantes.PROP_URL_BASE);

        String retorno = null;

        try {
            retorno = requisicao.get(urlBase.concat(Constantes.ENDPOINT_LOGIN), parametros);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Objects.nonNull(retorno)){
            return true;
        }
        return false;
    }

    @Override
    public boolean testarConexao(String urlBase) throws IOException {
        return requisicao.testeConexao(urlBase);
    }

}
