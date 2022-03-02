package com.everton.cashflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ContaController implements Initializable {

    @FXML private Button btnExporportarContas;
    @FXML private Button btnNovoContas;
    @FXML private Button btnPesquisarContas;
    @FXML private TableView<?> tbContas;
    @FXML private TableColumn<?, ?> colAcoesContas;
    @FXML private TableColumn<?, ?> colCodContas;
    @FXML private TableColumn<?, ?> colNomeContas;
    @FXML private TextField txtPesquisaContas;
    @FXML private TitledPane titledPaneContas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        fabricarColunas();
    }
//
//    ContaService  contaService = ContaService.getInstance();

//    private ObservableList<ClienteSimpleProperty> obterContas(){
//        List<Conta> contas = contaService.listarTodos();
//        List<ClienteSimpleProperty> clienteSimpleProperties = new ArrayList<>();
//        for(Conta conta : contas){
//            clienteSimpleProperties.add(new ClienteSimpleProperty(conta.getId(), conta.())
//            );
//        }
//
//        return FXCollections.observableArrayList(clienteSimpleProperties);
//    }
//
//    @FXML
//    private void abrirCadastroCliente(){
//        fabricarColunas();
//    }
//
//    private void fabricarColunas(){
////       colAcoesClientes.setCellValueFactory(new PropertyValueFactory<>("id"));
//        colNomeContas.setCellValueFactory(c -> c.getValue().nomeClienteProperty());
//        colCodContas.setCellValueFactory(c -> c.getValue().idProperty().asObject());
//        tbContas.setItems(obterContas());
//    }

}
