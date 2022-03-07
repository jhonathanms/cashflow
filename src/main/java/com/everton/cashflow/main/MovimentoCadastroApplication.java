package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Movimento;
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

public class MovimentoCadastroApplication extends Application {

    private static Stage stage;
    private static Scene scene;
    private Movimento movimento;
    private static MovimentoCadastroApplication movimentoCadastroApplication;

    public static MovimentoCadastroApplication getInstance() {
        if (Objects.nonNull(movimentoCadastroApplication)){
            return movimentoCadastroApplication;
        }
        movimentoCadastroApplication = new MovimentoCadastroApplication();
        return movimentoCadastroApplication;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public void abrirTelaAlterarMovimento(Stage stage, Movimento movimento) {
        this.movimento = movimento;
        start(stage);
    }

    public Movimento getMovimento() {
        return movimento;
    }

    @SneakyThrows
    @Override
    public void start(Stage stage){
        this.stage = stage;
        Parent root = FXMLLoader.load(MovimentoCadastroApplication.class
                .getResource(Constantes.CAMINHO_TELA_CADASTRO_MOVIMENTO));
        configStage(stage, root);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage,  Parent root){
        new FadeIn(root).play();
        scene = new Scene(root);
        stage.setTitle("Novo Movimento");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image(MovimentoCadastroApplication.class
                .getResource(Constantes.URL_LOGO_ICON).toString()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}