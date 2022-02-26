package com.everton.cashflow.models.clients.implementacoes;

import com.everton.cashflow.models.clients.interfaces.LoginClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import com.everton.cashflow.models.dto.UsuarioDTO;
import com.everton.cashflow.util.ConversorUtil;
import com.everton.cashflow.util.ExtracaoDeDados;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class LoginClientImpl implements LoginClient {

    private static LoginClientImpl loginClient;
    private RestTemplate<UsuarioDTO> restTemplate;

    @Override
    public LoginClient getInstance(){
        return Objects.nonNull(loginClient)
                ? loginClient
                : new LoginClientImpl();
    }

    public LoginClientImpl() {
        this.restTemplate = RestTemplate.getInstance();
    }

    @Override
    public boolean autenticacao(Usuario usuario){
        Properties props = ExtracaoDeDados.obterPropriedades();
        assert props != null;
        String urlBase = props.getProperty(Constantes.PROP_URL_BASE);
        String jsonUsuario = new ConversorUtil<UsuarioDTO>()
                .converterEntidadeEmJson(UsuarioDTO.converterParaDto(usuario));

        try {
           return restTemplate.post(urlBase.concat(Constantes.ENDPOINT_USUARIOS_LOGAR), jsonUsuario);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean testarConexao(String urlBase) throws IOException {
        return restTemplate.testeConexao(urlBase);
    }



}
