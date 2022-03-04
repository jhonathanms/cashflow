package com.everton.cashflow.controller;

import com.everton.cashflow.main.ClienteApplication;
import com.everton.cashflow.main.ProdutoApplication;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.entidades.Produto;
import com.everton.cashflow.models.parsers.ClienteSimpleProperty;
import com.everton.cashflow.models.parsers.ProdutosSimpleProperty;
import com.everton.cashflow.models.services.ClienteService;
import com.everton.cashflow.models.services.ContaService;
import com.everton.cashflow.models.services.ProdutoService;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CadastroController implements Initializable {

    @FXML
    private TextField txtPesquisaClientes;
    @FXML
    private Button btnNovoClientes;
    @FXML
    private Button btnExporportarClientes;
    @FXML
    protected TableView<ClienteSimpleProperty> tbClientes;
    @FXML
    protected TableColumn<ClienteSimpleProperty, String> colNomeClientes;
    @FXML
    protected TableColumn<ClienteSimpleProperty, Long> colCodClientes;
    @FXML
    protected TableColumn<ClienteSimpleProperty, ClienteSimpleProperty> colBtnDeleteCliente;
    @FXML
    protected TableColumn<ClienteSimpleProperty, ClienteSimpleProperty> colBtnEditarCliente;
    @FXML
    private Button btnPesquisarClientes;
    @FXML
    private TitledPane titledPaneClientes;

    @FXML
    private Button btnExporportarContas;
    @FXML
    private Button btnNovoContas;
    @FXML
    private Button btnPesquisarContas;
    @FXML
    private TableView<?> tbContas;
    @FXML
    private TableColumn<?, ?> colAcoesContas;
    @FXML
    private TableColumn<?, ?> colCodContas;
    @FXML
    private TableColumn<?, ?> colNomeContas;

    @FXML
    private Button btnPesquisarProdutos;
    @FXML
    private Button btnNovoProdutos;
    @FXML
    private Button btnExporportarProdutos;
    @FXML
    protected TableView<ProdutosSimpleProperty> tbProdutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, Long> colCodProdutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, String> colDescricaoodutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, Integer> colEstoqueProdutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, String> colValorProdutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, ProdutosSimpleProperty> colBtnDeleteProdutos;
    @FXML
    protected TableColumn<ProdutosSimpleProperty, ProdutosSimpleProperty> colBtnEditarProdutos;

    @FXML
    private TextField txtPesquisaContas;
    @FXML
    private TextField txtPesquisaProdutos;
    @FXML
    private Accordion painelAcordeon;
    @FXML
    private TitledPane titledPaneContas;
    @FXML
    private TitledPane titledPaneProdutos;

    private ClienteService clienteService = ClienteService.getInstance();
    private ProdutoService produtoService = ProdutoService.getInstance();
    private ContaService contaService = ContaService.getInstance();
    private ProdutoApplication produtoApplication = ProdutoApplication.getInstance();
    private ClienteApplication clienteApplication = ClienteApplication.getInstance();
    private static CadastroController cadastroController;

    public static CadastroController getInstance() {
        if (Objects.nonNull(cadastroController)) {
            return cadastroController;
        }
        cadastroController = new CadastroController();
        return cadastroController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fabricarColTableClientes(obterClientes());
        fabricarColTableProdutos(obterProdutos());
        habilitarPesquisaPorTeclaEnter();
    }

    @FXML
    private void abrirCadastroCliente() {
        clienteApplication.abrirTelaAlterarCliente(new Stage(), null);
        ClienteApplication.getStage().setOnCloseRequest(we -> {
            fabricarColTableClientes(obterClientes());
        });
    }

    @FXML
    private void abrirCadastroProduto() {
        produtoApplication.abrirTelaAlterarProduto(new Stage(), null);
        ProdutoApplication.getStage().setOnCloseRequest(we -> {
            fabricarColTableProdutos(obterProdutos());
        });
    }

    @FXML
    private void abrirCadastroUsuario() {
    }

    @FXML
    private ObservableList<ClienteSimpleProperty> obterClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        List<ClienteSimpleProperty> clienteSimpleProperties =
                clientes.stream()
                        .map(ClienteSimpleProperty::converterParaSimpleProperty)
                        .collect(Collectors.toList());
        return FXCollections.observableArrayList(clienteSimpleProperties);
    }

    @FXML
    private ObservableList<ProdutosSimpleProperty> obterProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        List<ProdutosSimpleProperty> produtosSimpleProperties =
                produtos.stream()
                        .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                        .collect(Collectors.toList());
        return FXCollections.observableArrayList(produtosSimpleProperties);
    }

    public void fabricarColTableClientes(ObservableList<ClienteSimpleProperty> clientes) {
        colNomeClientes.setCellValueFactory(c -> c.getValue().nomeClienteProperty());
        colCodClientes.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        tbClientes.setItems(clientes);

        TabelaUtil.initButtons(
                colBtnEditarCliente,
                15,
                Constantes.CAMINHO_ICONE_EDIT,
                "icone-edit",
                (ClienteSimpleProperty cliente, ActionEvent event) -> {
                    clienteApplication.abrirTelaAlterarCliente(new Stage(),
                            ClienteSimpleProperty.converterParaEntidade(cliente));

                    ClienteApplication.getStage().setOnCloseRequest(we -> {
                        fabricarColTableClientes(obterClientes());
                    });
                });

        TabelaUtil.initButtons(
                colBtnDeleteCliente,
                15,
                Constantes.CAMINHO_ICONE_DELETE,
                "icone-delete",
                (ClienteSimpleProperty cliente, ActionEvent event) -> {
                    boolean sucesso = clienteService.deletar(cliente.getId());
                    if (sucesso) {
                        fabricarColTableClientes(obterClientes());
                    }
                });
    }

    public void fabricarColTableProdutos(ObservableList<ProdutosSimpleProperty> produtos) {
        colCodProdutos.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colDescricaoodutos.setCellValueFactory(p -> p.getValue().nomeProdutoProperty());
        colEstoqueProdutos.setCellValueFactory(p -> p.getValue().estoqueProperty().asObject());
        colValorProdutos.setCellValueFactory(p -> p.getValue().valorProdutoProperty());
        tbProdutos.setItems(produtos);

        TabelaUtil.initButtons(
                colBtnEditarProdutos,
                15,
                Constantes.CAMINHO_ICONE_EDIT,
                "icone-edit",
                (ProdutosSimpleProperty produto, ActionEvent event) -> {
                    produtoApplication.abrirTelaAlterarProduto(new Stage(),
                            ProdutosSimpleProperty.converterParaEntidade(produto));

                    ProdutoApplication.getStage().setOnCloseRequest(we ->
                            fabricarColTableProdutos(obterProdutos()));
                });

        TabelaUtil.initButtons(
                colBtnDeleteProdutos,
                15,
                Constantes.CAMINHO_ICONE_DELETE,
                "icone-delete",
                (ProdutosSimpleProperty produto, ActionEvent event) -> {
                    boolean sucesso = produtoService.deletar(produto.getId());
                    if (sucesso) {
                        fabricarColTableProdutos(obterProdutos());
                    }
                });
    }

    @FXML
    private void pesquisarCliente() {
        String nomePesquisa = ExtracaoDeDados.textFieldToString(txtPesquisaClientes);
        if (Objects.isNull(nomePesquisa) || nomePesquisa.isEmpty()) {
            fabricarColTableClientes(obterClientes());
        } else {
            List<Cliente> clientes = clienteService.pesquisaPorNome(nomePesquisa);
            if (Objects.nonNull(clientes) || clientes.isEmpty()) {
                fabricarColTableClientes(
                        FXCollections.observableArrayList(
                                clientes.stream()
                                        .map(ClienteSimpleProperty::converterParaSimpleProperty)
                                        .collect(Collectors.toList()))
                );

            } else {
                fabricarColTableClientes(FXCollections.observableArrayList());
            }
        }
    }

    @FXML
    private void pesquisarProduto() {
        String nomePesquisa = ExtracaoDeDados.textFieldToString(txtPesquisaProdutos);
        if (Objects.isNull(nomePesquisa) || nomePesquisa.isEmpty()) {
            fabricarColTableProdutos(obterProdutos());
        } else {
            List<Produto> produtos = produtoService.pesquisaPorNome(nomePesquisa);
            if (Objects.nonNull(produtos) || produtos.isEmpty()) {
                fabricarColTableProdutos(
                        FXCollections.observableArrayList(
                                produtos.stream()
                                        .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                                        .collect(Collectors.toList())
                        )
                );
            } else {
                fabricarColTableProdutos(FXCollections.observableArrayList());
            }
        }
    }

    private void habilitarPesquisaPorTeclaEnter(){
        txtPesquisaClientes.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                pesquisarCliente();
            }
        });
        txtPesquisaProdutos.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
                pesquisarProduto();
            }
        });
    }

    @FXML private void exportarPDF(){
        AlertsUtil.alertaSimples("Informação", "Em desenvolvimento...", Alert.AlertType.INFORMATION);
    }
}
