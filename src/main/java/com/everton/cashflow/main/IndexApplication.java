package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import com.everton.cashflow.models.constantes.Constantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;

public class IndexApplication extends Application {

    private static Stage stage;
    private static Scene scene;
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

    public static Scene getScene() {
        return scene;
    }

    @SneakyThrows
    @Override
    public void start(Stage stage){
        this.stage = stage;
        Parent root = FXMLLoader.load(IndexApplication.class.getResource(Constantes.CAMINHO_TELA_INDEX));
        configStage(stage, root);
    }

    public static void main(String[] args) {
        launch();
    }

    private static void configStage(Stage stage,  Parent root){
        new FadeIn(root).play();
        scene = new Scene(root);
        stage.setTitle("Cashflow");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image(IndexApplication.class
                .getResource(Constantes.URL_LOGO_ICON).toString()));
        carregarSubTelas();
        stage.show();
    }

    private static void carregarSubTelas(){
        try {
            Parent root = FXMLLoader.load(IndexApplication.class.getResource(Constantes.CAMINHO_TELA_SECTION_HOME));
            AnchorPane telaHome = (AnchorPane) root;

            BorderPane borderPane = (BorderPane) ((AnchorPane)scene.getRoot()).getChildren().get(0);
            AnchorPane telaIndexCentro = (AnchorPane)borderPane.getCenter();

            telaIndexCentro.getChildren().addAll(telaHome.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}