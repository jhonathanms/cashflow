package com.everton.cashflow.models.services;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.everton.cashflow.models.clients.implementacoes.LoginClientImpl;
import com.everton.cashflow.models.clients.interfaces.LoginClient;
import com.everton.cashflow.models.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
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
    public void fecharTela(InputEvent event, Node node) {
        new FadeIn(node).play();
        node.toFront();
    }

    public boolean acessar(Usuario usuario){
        try {
            return loginClient.autenticacao(usuario);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SneakyThrows
    public boolean salvarConfig(String propriedade, String valor){
        File file = new File(LoginService.class.
                getResource("configuracao.properties").toString());
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

    public void testarConexao(String urlBase){
        loginClient.testarConexao(urlBase);
    }

}
