package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

import java.util.Objects;

public class ClienteApplication extends Application {

    private static Stage stage;
    private static Scene scene;
    private Cliente cliente;
    private static ClienteApplication indexApplication;

    public static ClienteApplication getInstance() {
        if (Objects.nonNull(indexApplication)){
            return indexApplication;
        }
        indexApplication = new ClienteApplication();
        return indexApplication;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public void abrirTelaAlterarCliente(Stage stage, Cliente cliente ) {
        this.cliente = cliente;
        start(stage);
    }

    public Cliente getCliente() {
        return cliente;
    }

    @SneakyThrows
    @Override
    public void start(Stage stage){
        this.stage = stage;
        Parent root = FXMLLoader.load(ClienteApplication.class
                .getResource(Constantes.CAMINHO_TELA_CADASTRO_CLIENTES));
        configStage(stage, root);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage,  Parent root){
        new FadeIn(root).play();
        scene = new Scene(root);
        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image(ClienteApplication.class
                .getResource(Constantes.URL_LOGO_ICON).toString()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}