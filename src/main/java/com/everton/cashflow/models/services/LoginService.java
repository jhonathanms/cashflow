package com.everton.cashflow.models.services;

import animatefx.animation.FadeIn;
import com.everton.cashflow.models.clients.implementacoes.LoginClientImpl;
import com.everton.cashflow.models.clients.interfaces.LoginClient;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import javafx.event.Event;
import javafx.scene.Node;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

public class LoginService extends GenericService{
    private static LoginService loginService;
    private LoginClient loginClient;

    public static LoginService getInstance(){
        return Objects.nonNull(loginService)
                ? loginService
                : new LoginService();
    }

    public LoginService() {
        this.loginClient = new LoginClientImpl();
    }

    @Override
    public void fecharTela(Event event, Node node) {
        System.exit(0);
    }

    public boolean acessar(Usuario usuario){
        try {
            return loginClient.autenticacao(usuario);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // http://localhost:8877/v1/configuracoes
    @SneakyThrows
    public boolean salvarConfig(String propriedade, String valor){
        String path = LoginService.class.
                getResource("/"+Constantes.CONFIG_PROPERTIES).toString();
        File file = new File(path);
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file)){
            properties.load(fis);
            properties.store(fos, "Atualizado em:" + LocalDateTime.now());
            return true;
        }catch (IOException ex) {
            System.out.println(ex.getMessage()); ex.printStackTrace();
            return false;
        }
    }

    public void testarConexao(String urlBase) throws IOException {
        loginClient.testarConexao(urlBase);
    }

}
