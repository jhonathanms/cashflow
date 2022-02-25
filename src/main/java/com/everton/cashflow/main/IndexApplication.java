package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;

public class IndexApplication extends Application {

    private static Stage stage;
    private static IndexApplication indexApplication;

    public static IndexApplication getInstance() {
        if (Objects.nonNull(indexApplication)){
            return indexApplication;
        }
        indexApplication = new IndexApplication();
        return indexApplication;
    }

    public static Stage getStage() {
        return stage;
    }

    @SneakyThrows
    @Override
    public void start(Stage stage){
        this.stage = stage;
        Parent root = FXMLLoader.load(IndexApplication.class.getResource("/fxmls/index.fxml"));
        new FadeIn(root).play();
        Scene scene = new Scene(root);
        configStage(stage, scene);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage, Scene scene){
        stage.setTitle("Cashflow");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(IndexApplication.class
                .getResource("/imagens/icons/icon-titulo.png").toString()));
        stage.show();

    }
}