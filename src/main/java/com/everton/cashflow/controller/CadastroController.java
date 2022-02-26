package com.everton.cashflow.controller;

import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.parsers.ClienteSimpleProperty;
import com.everton.cashflow.models.services.ClienteService;
import com.everton.cashflow.models.services.ContaService;
import com.everton.cashflow.models.services.ProdutoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    @FXML private Button btnExporportarClientes;
    @FXML private Button btnExporportarContas;
    @FXML private Button btnExporportarProdutos;
    @FXML private Button btnNovoClientes;
    @FXML private Button btnNovoContas;
    @FXML private Button btnNovoProdutos;
    @FXML private TextField btnPesquisarClientes;
    @FXML private Button btnPesquisarContas;
    @FXML private Button btnPesquisarProdutos;

    @FXML private TableView<ClienteSimpleProperty> tbClientes;
    @FXML private TableColumn<ClienteSimpleProperty, ?> colAcoesClientes;
    @FXML private TableColumn<ClienteSimpleProperty, String> colNomeClientes;
    @FXML private TableColumn<ClienteSimpleProperty, Long> colCodClientes;

    @FXML private TableView<?> tbContas;
    @FXML private TableColumn<?, ?> colAcoesContas;
    @FXML private TableColumn<?, ?> colCodContas;
    @FXML private TableColumn<?, ?> colNomeContas;

    @FXML private TableView<?> tbProdutos;
    @FXML private TableColumn<?, ?> colAcoesProdutos;
    @FXML private TableColumn<?, ?> colCodProdutos;
    @FXML private TableColumn<?, ?> colDescricaoodutos;
    @FXML private TableColumn<?, ?> colEstoqueProdutos;

    @FXML private TableColumn<?, ?> colValorProdutos;
    @FXML private Button txtPesquisaClientes;
    @FXML private TextField txtPesquisaContas;
    @FXML private TextField txtPesquisaProdutos;
    @FXML private Accordion painelAcordeon;
    @FXML private TitledPane titledPaneClientes;
    @FXML private TitledPane titledPaneContas;
    @FXML private TitledPane titledPaneProdutos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fabricarColunas();
        colNomeClientes.setCellValueFactory(c -> c.getValue().nomeClienteProperty());
        colCodClientes.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        tbClientes.setItems(obterTodosOsClientes());
    }

    ClienteService clienteService = ClienteService.getInstance();
    ProdutoService produtoService = ProdutoService.getInstance();
    ContaService  contaService = ContaService.getInstance();

    private ObservableList<ClienteSimpleProperty> obterTodosOsClientes(){
        List<Cliente> clientes = clienteService.listarTodos();
        List<ClienteSimpleProperty> clienteSimpleProperties = new ArrayList<>();
        for(Cliente cliente : clientes){
            clienteSimpleProperties.add(new ClienteSimpleProperty(cliente.getId(), cliente.getNomeCliente())
            );
        }

        return FXCollections.observableArrayList(clienteSimpleProperties);
    }

    @FXML
    private void abrirCadastroCliente(){
        fabricarColunas();
    }

    private void fabricarColunas(){
//       colAcoesClientes.setCellValueFactory(new PropertyValueFactory<>("id"));

    }

}
