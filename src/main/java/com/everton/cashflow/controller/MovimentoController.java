package com.everton.cashflow.controller;

import com.everton.cashflow.main.MovimentoApplication;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.parsers.MovimentoSimpleProperty;
import com.everton.cashflow.models.services.MovimentoService;
import com.everton.cashflow.util.AlertsUtil;
import com.everton.cashflow.util.ExtracaoDeDados;
import com.everton.cashflow.util.TabelaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MovimentoController implements Initializable {
    @FXML
    private Button btnExporportarMovimento;

    @FXML
    private Button btnNovoMovimento;

    @FXML
    private Button btnPesquisarMovimento;

    @FXML
    private TableColumn<MovimentoSimpleProperty, Long> colCodigo;

    @FXML
    private TableColumn<MovimentoSimpleProperty, String> colDataEmissao;

    @FXML
    private TableColumn<MovimentoSimpleProperty, MovimentoSimpleProperty> colEditMovimento;

    @FXML
    private TableColumn<MovimentoSimpleProperty, MovimentoSimpleProperty> colImprimirMovimento;

    @FXML
    private TableColumn<MovimentoSimpleProperty, String> colModelo;

    @FXML
    private TableColumn<MovimentoSimpleProperty, String> colNatureza;

    @FXML
    private TableColumn<MovimentoSimpleProperty, String> colPessoa;

    @FXML
    private TableColumn<MovimentoSimpleProperty, String> colValor;

    @FXML
    private Label lblQuantidade;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<MovimentoSimpleProperty> tbMovimento;

    @FXML
    private TextField txtPesquisaMovimento;

    private MovimentoService movimentoService = MovimentoService.getInstance();
    private MovimentoApplication movimentoApplication = MovimentoApplication.getInstance();

    @FXML
    void abrirCadastroMovimento(ActionEvent event) {
        AlertsUtil.alertaSimples("Informação", "Recurso em desenvolvimento...", Alert.AlertType.INFORMATION);
    }

    @FXML
    private ObservableList<MovimentoSimpleProperty> obterMovimentos() {
        List<Movimento> movimentos = movimentoService.listarTodos();
        List<MovimentoSimpleProperty> movimentoSimplePropertyList =
                movimentos.stream()
                        .map(MovimentoSimpleProperty::converterParaSimpleProperty)
                        .collect(Collectors.toList());
        return FXCollections.observableArrayList(movimentoSimplePropertyList);
    }

    public void fabricarColTableMovimentos(ObservableList<MovimentoSimpleProperty> movimentos) {
        colCodigo.setCellValueFactory(m -> m.getValue().idProperty().asObject());
        colPessoa.setCellValueFactory(c -> c.getValue().clienteProperty());
        colModelo.setCellValueFactory(c -> c.getValue().modeloProperty());
        colNatureza.setCellValueFactory(c -> c.getValue().naturezaProperty());
        colDataEmissao.setCellValueFactory(c -> c.getValue().dataVendaProperty());
        colValor.setCellValueFactory(c -> c.getValue().valorProperty());
        tbMovimento.setItems(movimentos);

        TabelaUtil.initButtons(
                colEditMovimento,
                15,
                Constantes.CAMINHO_ICONE_EDIT,
                "icone-edit",
                (MovimentoSimpleProperty movimento, ActionEvent event) -> {
                    AlertsUtil.alertaSimples("Informação", "Recurso em desenvolvimento...", Alert.AlertType.INFORMATION);
                });

        TabelaUtil.initButtons(
                colImprimirMovimento,
                15,
                Constantes.CAMINHO_ICONE_IMPRIMIR,
                "icone-imprimir",
                (MovimentoSimpleProperty movimento, ActionEvent event) -> {
            AlertsUtil.alertaSimples("Informação", "Recurso em desenvolvimento...", Alert.AlertType.INFORMATION);
        });
        lblQuantidade.setText(String.valueOf(movimentoService.obterQuantidadeMovimentos(movimentos)));
        lblTotal.setText(movimentoService.obterTotalMovimentos(movimentos));
    }

    @FXML
    private void pesquisarMovimento() {
        String nomePesquisa = ExtracaoDeDados.textFieldToString(txtPesquisaMovimento);
        if (Objects.isNull(nomePesquisa) || nomePesquisa.isEmpty()) {
            fabricarColTableMovimentos(obterMovimentos());
        } else {
            List<Movimento> movimentos = movimentoService.pesquisaPorNome(nomePesquisa);
            if (Objects.nonNull(movimentos) || movimentos.isEmpty()) {
                fabricarColTableMovimentos(FXCollections.observableArrayList(movimentos.stream().map(MovimentoSimpleProperty::converterParaSimpleProperty).collect(Collectors.toList())));
            } else {
                fabricarColTableMovimentos(FXCollections.observableArrayList());
            }
        }
    }

    private void habilitarPesquisaPorTeclaEnter() {
        txtPesquisaMovimento.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                pesquisarMovimento();
            }
        });
    }

    @FXML
    private void exportarPDF() {
        AlertsUtil.alertaSimples("Informação", "Em desenvolvimento...", Alert.AlertType.INFORMATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPesquisaMovimento.requestFocus();
        fabricarColTableMovimentos(obterMovimentos());
        habilitarPesquisaPorTeclaEnter();
    }

}
