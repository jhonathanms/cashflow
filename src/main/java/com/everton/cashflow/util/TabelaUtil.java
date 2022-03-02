package com.everton.cashflow.util;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.shape.SVGPath;
import javafx.util.Callback;

import java.util.function.BiConsumer;

public class TabelaUtil {

    public static <T> void initButtons(TableColumn<T, T> tableColumn,
                                       int size,
                                       String svgIcon,
                                       String colorClassName,
                                       BiConsumer<T, ActionEvent> buttonAction) {

        final int COLUMN_ICON_SPACE = 20;
        tableColumn.setMinWidth(size + COLUMN_ICON_SPACE);
        tableColumn.setMaxWidth(size + COLUMN_ICON_SPACE);
        tableColumn.setStyle("-fx-alignment: CENTER");

        Callback<TableColumn<T, T>, TableCell<T, T>> cellFactory = new Callback<>() {
            @Override
            public TableCell<T, T> call(final TableColumn<T, T> param) {
                return new TableCell<>() {

                    private final Button btn = createIconButton(svgIcon, size, colorClassName);

                    // inserir botão dentro da célula
                    @Override
                    public void updateItem(T item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }

                    {
                        // define o que irá acontecer quando o botão for clicado
                        btn.setOnAction((ActionEvent event) -> {
                            T data = getTableView().getItems().get(getIndex());
                            buttonAction.accept(data, event);
                        });
                    }
                };
            }
        };
        tableColumn.setCellFactory(cellFactory);
    }

    // Cria um botão com o ícone svg dentro
    public static Button createIconButton(String svgAbsolutePath, int size, String colorClassName) {
        SVGPath path = new SVGPath();
        path.setContent(svgAbsolutePath);
        Bounds bounds = path.getBoundsInLocal();

        // scale to size size x size (max)
        double scaleFactor = size / Math.max(bounds.getWidth(), bounds.getHeight());
        path.setScaleX(scaleFactor);
        path.setScaleY(scaleFactor);
        path.getStyleClass().add(colorClassName); // define a cor do ícone

        Button button = new Button();
        button.setPickOnBounds(true); // Garente que o botão terá o fundo transparente
        button.setGraphic(path); // inseri o ícone gerado pelo svg no botão
        button.setAlignment(Pos.CENTER);
        button.getStyleClass().add("icon-button"); // classe criada para não ter o fundo cinza
        // necessários para o ícone ficar contido dentro do botão
        button.setMaxWidth(size);
        button.setMaxHeight(size);
        button.setMinWidth(size);
        button.setMinHeight(size);
        button.setPrefWidth(size);
        button.setPrefHeight(size);
        return button;
    }
}
