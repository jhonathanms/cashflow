package com.everton.cashflow.controller;

import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.services.ClienteService;
import com.everton.cashflow.models.services.ContaService;
import com.everton.cashflow.models.services.ProdutoService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    @FXML private TableColumn<Cliente, ?> colAcoesClientes;
    @FXML private TableColumn<Cliente, ?> colNomeClientes;
    @FXML private TableColumn<Cliente, ?> colCodClientes;

    @FXML
    private TableColumn<?, ?> colAcoesContas;

    @FXML
    private TableColumn<?, ?> colAcoesProdutos;



    @FXML
    private TableColumn<?, ?> colCodContas;

    @FXML
    private TableColumn<?, ?> colCodProdutos;

    @FXML
    private TableColumn<?, ?> colDescricaoodutos;

    @FXML
    private TableColumn<?, ?> colEstoqueProdutos;

    @FXML
    private TableColumn<?, ?> colNomeContas;

    @FXML
    private TableColumn<?, ?> colValorProdutos;

    @FXML
    private TableView<?> tbClientes;

    @FXML
    private TableView<?> tbContas;

    @FXML
    private TableView<?> tbProdutos;

    @FXML
    private Button txtPesquisaClientes;

    @FXML
    private TextField txtPesquisaContas;

    @FXML
    private TextField txtPesquisaProdutos;

    @FXML
    private Accordion painelAcordeon;

    @FXML
    private static TitledPane titledPaneClientes;

    @FXML
    private TitledPane titledPaneContas;

    @FXML
    private TitledPane titledPaneProdutos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    ClienteService clienteService = ClienteService.getInstance();
    ProdutoService produtoService = ProdutoService.getInstance();
    ContaService  contaService = ContaService.getInstance();
    @FXML
    private void listarTodosOsCliente(){
        List<Cliente> clientes = clienteService.listarTodos();
    }

    @FXML
    private void abrirCadastroCliente(){
    }

}
