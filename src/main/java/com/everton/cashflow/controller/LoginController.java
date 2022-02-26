package com.everton.cashflow.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.everton.cashflow.main.IndexApplication;
import com.everton.cashflow.main.LoginApplication;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.services.LoginService;
import com.everton.cashflow.util.Alerts;
import com.everton.cashflow.util.ExtracaoDeDados;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    private LoginService loginService = LoginService.getInstance();
    private IndexApplication indexApplication = IndexApplication.getInstance();

    @FXML
    private void fecharTela(){
        (new FadeOut(this.root)).play();
            System.exit(0);
    }

    @FXML
    private void acessar(){
        String login = ExtracaoDeDados.parseToString(txtUsuario);
        String senha = ExtracaoDeDados.parseToString(txtSenha);

//        if(ehNuloOuVazio(login) || ehNuloOuVazio(senha)){
//            Alerts.alertaSimples(
//                    "Autenticação",
//                    "Login e/ou Senha não pode(m) ficar vazio(s)",
//                    Alert.AlertType.WARNING);
//            txtUsuario.requestFocus();
//        }else{
//            Usuario usuario = new Usuario(login, senha);
//            boolean sucesso = loginService.acessar(usuario);
//            if(sucesso) {
//                indexApplication.start(new Stage());
//                LoginApplication.getStage().close();
//            }else{
//                Alerts.alertaSimples(
//                        "Autenticação",
//                        "Ops! Login e/ou senha incorreto",
//                        Alert.AlertType.WARNING
//                );
//                txtUsuario.selectAll();
//                txtSenha.clear();
//                txtUsuario.requestFocus();
//            }
//        }
        indexApplication.start(new Stage());
        LoginApplication.getStage().close();
    }

    @FXML
    private void configuracao() {
        (new FadeIn(this.paneConfig)).play();
        this.paneConfig.toFront();
        String urlBase = ExtracaoDeDados.obterPropriedades()
                .getProperty(Constantes.PROP_URL_BASE);

        if(ehNuloOuVazio(urlBase)){
            txtUrl.requestFocus();
        }else{
            txtUrl.setText(urlBase);
        }
    }

    private boolean ehNuloOuVazio(String str){
        return Objects.isNull(str) || str.isEmpty();
    }

    @FXML
    private void salvarConfig(){
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
    private void cancelar(){
        new FadeOut(paneConfig).play();
        paneConfig.toBack();
    }

    @FXML
    private void testarConexao(){
        String urlBase = ExtracaoDeDados.parseToString(txtUrl);

        try {
            loginService.testarConexao(urlBase);
            Alerts.alertaSimples(
                    "Teste de conexão",
                    "Conexão realizada com sucesso!",
                    Alert.AlertType.INFORMATION
            );
        } catch (IOException e) {
            Alerts.alertException(
                    "Teste de conexão",
                    "Erro ao tentar se conectar com o servidor",
                    e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsuario.requestFocus();
    }

}

