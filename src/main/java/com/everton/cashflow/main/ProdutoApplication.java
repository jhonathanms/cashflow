package com.everton.cashflow.main;

import animatefx.animation.FadeIn;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Produto;
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

public class ProdutoApplication extends Application {

    private static Stage stage;
    private static Scene scene;
    private static ProdutoApplication produtoApplication;
    private Produto produto;

    public static ProdutoApplication getInstance() {
        if (Objects.nonNull(produtoApplication)){
            return produtoApplication;
        }
        produtoApplication = new ProdutoApplication();
        return produtoApplication;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public void abrirTelaAlterarProduto(Stage stage, Produto produto ) {
        this.produto = produto;
        start(stage);
    }

    public Produto getProduto() {
        return produto;
    }

    @SneakyThrows
    @Override
    public void start(Stage stage){
        this.stage = stage;
        Parent root = FXMLLoader.load(ProdutoApplication.class
                .getResource(Constantes.CAMINHO_TELA_CADASTRO_PRODUTOS));
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
        stage.getIcons().add(new Image(ProdutoApplication.class
                .getResource(Constantes.URL_LOGO_ICON).toString()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}