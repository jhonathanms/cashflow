package com.everton.cashflow.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import com.everton.cashflow.models.services.LoginService;
import com.everton.cashflow.util.Alerts;
import com.everton.cashflow.util.ExtracaoDeDados;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnAcessar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnTestarConn;

    @FXML
    private Label lblConfig;

    @FXML
    private Pane paneConfig;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtUsuario;

    @FXML
    private AnchorPane root;

    LoginService loginService = LoginService.getInstance();

    @FXML
    private void fecharTela(ActionEvent evento){
        (new FadeOut(this.root)).play();
        loginService.fecharTela(evento, paneConfig);
    }

    @FXML
    private void acessar(ActionEvent evento){
        String login = ExtracaoDeDados.parseToString(txtUsuario);
        String senha = ExtracaoDeDados.parseToString(txtSenha);

        Usuario usuario = new Usuario(login, senha);

        boolean sucesso = false;
        sucesso = loginService.acessar(usuario);

        if(!sucesso) Alerts.alertaSimples(
                "Autenticação",
                "Ops! Login e/ou senha incorreto",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    private void configuracao(MouseEvent evento) {
        (new FadeIn(this.paneConfig)).play();
        this.paneConfig.toFront();
    }

    @FXML
    private void salvarConfig(ActionEvent evento){
        String url = ExtracaoDeDados.parseToString(txtUrl);
        boolean sucesso = loginService.salvarConfig(Constantes.PROP_URL_BASE, url);
        if(!sucesso) Alerts.alertaSimples(
                "Configurações",
                "Ocorreu um problema ao tentar salvar.",
                Alert.AlertType.ERROR
        );
        new FadeOut(paneConfig).play();
        paneConfig.toBack();
    }

    @FXML
    private void cancelar(ActionEvent evento){
        new FadeOut(paneConfig).play();
        paneConfig.toBack();
    }

    @FXML
    private void testarConexao(ActionEvent evento){
        String urlBase = ExtracaoDeDados.parseToString(txtUrl);

        try {
            loginService.testarConexao(urlBase);
        } catch (IOException e) {
            Alerts.alertException(
                    "Teste de conexão",
                    "Erro ao tentar se conectar com o servidor",
                    e.getMessage());
            e.printStackTrace();
        }
        Alerts.alertaSimples(
                "Teste de conexão",
                "Conexão realizada com sucesso!",
                Alert.AlertType.INFORMATION
        );

    }
}

