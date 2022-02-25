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
import java.util.Objects;

public class LoginApplication extends Application {

    private static Stage stage;
    private static LoginApplication loginApplication;

    public static LoginApplication getInstance() {
        if (Objects.nonNull(loginApplication)){
            return loginApplication;
        }
        loginApplication = new LoginApplication();
        return loginApplication;
    }

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Parent root = FXMLLoader.load(LoginApplication.class.getResource("/fxmls/login.fxml"));
        new FadeIn(root).play();
        Scene scene = new Scene(root);
        configStage(stage, scene, root);
    }


    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage, Scene scene, Parent root){
        stage.setTitle("Login");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.getIcons().add(new Image(LoginApplication.class.getResource("/imagens/icons/icone-logo.png").toString()));
        carregarPainelPrincipal(root);
        stage.show();

    }

    private static void carregarPainelPrincipal(Parent root) {
        BorderPane borderPane = (BorderPane) root.getChildrenUnmodifiable().get(0) ;//BorderPane
        StackPane stackPane = (StackPane) borderPane.getChildren().get(1);
        Pane pane = (Pane) stackPane.getChildren().get(1);
        if (pane.getId().equals("paneConfig"))
            pane.toBack();
    }
}