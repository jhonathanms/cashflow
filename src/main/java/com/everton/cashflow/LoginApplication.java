package com.everton.cashflow;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDown;
import animatefx.animation.ZoomIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginApplication extends Application {

    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        Parent root = FXMLLoader.load(LoginApplication.class.getResource("/fxmls/loginFxml.fxml"));
        new FadeIn(root).play();
        Scene scene = new Scene(root);
        configStage(stage, scene);
    }
    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage, Scene scene){
        stage.setTitle("Login");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.getIcons().add(new Image(LoginApplication.class.getResource("/imagens/icons/icon-titulo.png").toString()));
        stage.show();
    }
}