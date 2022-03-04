package com.everton.cashflow.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.everton.cashflow.main.IndexApplication;
import com.everton.cashflow.main.LoginApplication;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Usuario;
import com.everton.cashflow.models.services.LoginService;
import com.everton.cashflow.util.AlertsUtil;
import com.everton.cashflow.util.ExtracaoDeDados;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox vbox_login;

    private LoginService loginService = LoginService.getInstance();
    private IndexApplication indexApplication = IndexApplication.getInstance();

    @FXML
    private void fecharTela(){
        (new FadeOut(this.root)).play();
        System.exit(0);
    }

    @FXML
    private void acessar(){
        String login = ExtracaoDeDados.textFieldToString(txtUsuario);
        String senha = ExtracaoDeDados.textFieldToString(txtSenha);

        if(ehNuloOuVazio(login) || ehNuloOuVazio(senha)){
            AlertsUtil.alertaSimples(
                    "Autenticação",
                    "Login e/ou Senha não pode(m) ficar vazio(s)",
                    Alert.AlertType.WARNING);
            txtUsuario.requestFocus();
        }else{
            txtUsuario.setDisable(true);
            txtSenha.setDisable(true);
            btnAcessar.setDisable(true);
            Usuario usuario = new Usuario(login, senha);
            boolean sucesso = loginService.acessar(usuario);
            if(sucesso) {
                txtUsuario.setDisable(false);
                txtSenha.setDisable(false);
                btnAcessar.setDisable(false);

                indexApplication.start(new Stage());
                LoginApplication.getStage().close();
            }else{
                txtUsuario.setDisable(false);
                txtSenha.setDisable(false);
                btnAcessar.setDisable(false);
                AlertsUtil.alertaSimples(
                        "Autenticação",
                        "Ops! Login e/ou senha incorreto",
                        Alert.AlertType.WARNING
                );
                txtUsuario.selectAll();
                txtSenha.clear();
                txtUsuario.requestFocus();
            }
        }
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
        String url = ExtracaoDeDados.textFieldToString(txtUrl);
        boolean sucesso = loginService.salvarConfig(Constantes.PROP_URL_BASE, url);
        if(!sucesso) AlertsUtil.alertaSimples(
                "Configurações",
                "Ocorreu um problema ao tentar salvar.",
                Alert.AlertType.ERROR
        );
        new FadeOut(paneConfig).play();
        paneConfig.toBack();
        txtUsuario.requestFocus();
    }

    @FXML
    private void cancelar(){
        new FadeOut(paneConfig).play();
        paneConfig.toBack();
        txtUsuario.requestFocus();
    }

    @FXML
    private void testarConexao(){
        String urlBase = ExtracaoDeDados.textFieldToString(txtUrl);

        try {
            loginService.testarConexao(urlBase);
            AlertsUtil.alertaSimples(
                    "Teste de conexão",
                    "Conexão realizada com sucesso!",
                    Alert.AlertType.INFORMATION
            );
        } catch (IOException e) {
            AlertsUtil.alertException(
                    "Teste de conexão",
                    "Erro ao tentar se conectar com o servidor",
                    e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> txtUsuario.requestFocus());
        txtSenha.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)) acessar();
        });
    }
}

