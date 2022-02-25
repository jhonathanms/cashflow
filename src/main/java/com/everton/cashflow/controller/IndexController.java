package com.everton.cashflow.controller;

import com.everton.cashflow.main.IndexApplication;
import com.everton.cashflow.models.constantes.Constantes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnFinanceiro;

    @FXML
    private Button btnMovimento;

    @FXML
    private Button btnPrincipal;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox grupoMenu;

    @FXML
    private void abrirHome(ActionEvent evento){
        carregarSubTela(Constantes.CAMINHO_TELA_SECTION_HOME);
      selecionarMenuTelaAtiva(evento, btnPrincipal.getId());
    }

    @FXML
    private void abrirMovimento(ActionEvent evento){
        carregarSubTela(Constantes.CAMINHO_TELA_SECTION_MOVIMENTO);
        selecionarMenuTelaAtiva(evento, btnMovimento.getId());
    }

    @FXML
    private void abrirFinanceiro(ActionEvent evento){
        carregarSubTela(Constantes.CAMINHO_TELA_SECTION_FINANCEIRO);
        selecionarMenuTelaAtiva(evento, btnFinanceiro.getId());
    }

    @FXML
    private void abrirCadastro(ActionEvent evento){
        carregarSubTela(Constantes.CAMINHO_TELA_SECTION_CADASTRO);
        selecionarMenuTelaAtiva(evento, btnCadastro.getId());
    }

    @FXML
    private void sair(){
       System.exit(0);
    }

    private synchronized void selecionarMenuTelaAtiva(ActionEvent evento, String idBotaoMenu){
        grupoMenu.getChildren().forEach(btn -> {
            if(btn instanceof Button){
                if (btn.getId().equals(idBotaoMenu)){
                    btn.setStyle("-fx-background-color:  #747474;" + "-fx-text-fill:#fff;");
                }else{
                    btn.setStyle("-fx-background-color:  #535353;" + "-fx-text-fill:#fff;");
                }
            }
        });
    }

    private void carregarSubTela(String urlTela){
        try {
            Parent root = FXMLLoader.load(IndexApplication.class.getResource(urlTela));
            AnchorPane novaTela = (AnchorPane) root;

            Scene scene = IndexApplication.getScene();

            BorderPane borderPane = (BorderPane) ((AnchorPane)scene.getRoot()).getChildren().get(0);
            AnchorPane telaIndexCentro = (AnchorPane)borderPane.getCenter();

            telaIndexCentro.getChildren().clear();
            telaIndexCentro.getChildren().addAll(novaTela.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnPrincipal.setStyle("-fx-background-color:  #747474;" + "-fx-text-fill:#fff;");
    }
}
