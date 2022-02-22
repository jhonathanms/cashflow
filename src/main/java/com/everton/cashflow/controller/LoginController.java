package com.everton.cashflow.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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

    @FXML
    private void buttonEvent(ActionEvent evento){
        if(evento.getSource() == btnFechar){
            new FadeOut(root).play();
            System.exit(0);
        }

        if(evento.getSource() == btnAcessar){

        }

        if(evento.getSource() == btnSalvar){
            new FadeOut(paneConfig).play();
            paneConfig.toBack();
        }

        if(evento.getSource() == btnCancelar){
            new FadeOut(paneConfig).play();
            paneConfig.toBack();
        }

        if(evento.getSource() == btnTestarConn){

        }
    }

    @FXML
    private void mouseEvent(MouseEvent evento){
        if(evento.getSource() == lblConfig){
            new FadeIn(paneConfig).play();
            paneConfig.toFront();
        }
    }

}

