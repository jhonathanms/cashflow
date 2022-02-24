package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class IndexApplication extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent root = FXMLLoader.load(IndexApplication.class.getResource("/fxmls/loginFxml.fxml"));
        new FadeIn(root).play();
        Scene scene = new Scene(root);
        configStage(stage, scene, root);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage, Scene scene, Parent root){
        stage.setTitle("Cashflow");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(IndexApplication.class
                .getResource("/imagens/icons/icon-titulo.png").toString()));
        stage.show();

    }
}