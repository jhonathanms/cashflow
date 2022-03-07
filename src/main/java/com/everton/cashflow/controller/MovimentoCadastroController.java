package com.everton.cashflow.controller;

import com.everton.cashflow.main.MovimentoApplication;
import com.everton.cashflow.main.MovimentoCadastroApplication;
import com.everton.cashflow.models.constantes.Constantes;
import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.entidades.Movimento;
import com.everton.cashflow.models.entidades.Produto;
import com.everton.cashflow.models.parsers.ClienteSimpleProperty;
import com.everton.cashflow.models.parsers.ProdutosSimpleProperty;
import com.everton.cashflow.models.services.ClienteService;
import com.everton.cashflow.models.services.MovimentoService;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MovimentoCadastroController implements Initializable {
    @FXML private Button btnAdicionarProduto;
    @FXML private Button btnCancelarMovimento;
    @FXML private Button btnSalvarMovimento;
    @FXML private ComboBox<ProdutosSimpleProperty> cbDescricao;
    @FXML private ComboBox<ClienteSimpleProperty> cbPessoa;
    @FXML private DatePicker dtEmissao;
    @FXML private Label lblTituloCadastroMovimento;
    @FXML private TableView<ProdutosSimpleProperty> tbItens;
    @FXML private TableColumn<ProdutosSimpleProperty, Long> colCodigo;
    @FXML private TableColumn<ProdutosSimpleProperty, String> colDescricao;
    @FXML private TableColumn<ProdutosSimpleProperty, Integer> colQtde;
    @FXML private TableColumn<ProdutosSimpleProperty, String> colValor;
    @FXML private TableColumn<ProdutosSimpleProperty, ProdutosSimpleProperty> colEditar;
    @FXML private TableColumn<ProdutosSimpleProperty, ProdutosSimpleProperty> colDelete;
    @FXML private TextField txtCodigoMovimento;
    @FXML private TextField txtCodigoProduto;
    @FXML private TextField txtQtdeProduto;
    @FXML private TextField txtValorUnitProduto;
    @FXML private TextField txtSubTotal;
    @FXML private TextField txtDescAcres;
    @FXML private TextField txtTotal;
    @FXML private ScrollPane scrollPanePrincipal;


    private MovimentoService movimentoService = MovimentoService.getInstance();
    private MovimentoApplication movimentoApplication = MovimentoApplication.getInstance();
    private static MovimentoCadastroController movimentoCadastroController;
    private ProdutoService produtoService = ProdutoService.getInstance();
    private ClienteService clienteService = ClienteService.getInstance();
    private List<Produto> itens = new LinkedList<>();

    public static MovimentoCadastroController getInstance() {
        if (Objects.nonNull(movimentoCadastroController)) {
            return movimentoCadastroController;
        }
        movimentoCadastroController = new MovimentoCadastroController();
        return movimentoCadastroController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exibirDadosDeProdutos();
        txtCodigoProduto.requestFocus();
        habilitarPesquisaPorTeclaEnter();
    }

    @FXML
    private void SalvarOuAtualizar() {
        Movimento movimento = movimentoApplication.getMovimento();
        if (Objects.isNull(movimento)) {
            cadastrarMovimento();
        } else {
            AlertsUtil.alertaSimples("Informação",
                    "Função em desenvolvimento...", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void adicionarItem(){
        long codigo = Long.parseLong(txtCodigoProduto.getText());
        String descricao = cbDescricao.getValue().toString();
        int qtde = Integer.parseInt(txtQtdeProduto.getText());
        double valor = qtde * Double.parseDouble(txtValorUnitProduto.getText());
        itens.add(new Produto(codigo, descricao, valor, qtde, null));
        fabricarColTableProdutos(
                        FXCollections.observableArrayList(itens.stream()
                                .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                                .collect(Collectors.toList())));

        txtCodigoProduto.setText("");
        txtQtdeProduto.setText("1");
        cbDescricao.setValue(null);
        txtValorUnitProduto.setText("0.00");
        txtCodigoProduto.requestFocus();
    }

    private void buscarItem(){
        long id = Long.parseLong(txtCodigoProduto.getText());
        Produto produto = produtoService.buscarPorId(id);
        cbDescricao.setValue(ProdutosSimpleProperty.converterParaSimpleProperty(produto));
        txtValorUnitProduto.setText(String.valueOf(produto.getValorProduto()));
        txtQtdeProduto.requestFocus();
    }

    private void cadastrarMovimento() {
        ObservableList<ClienteSimpleProperty> obs = cbPessoa.getItems();
        Cliente cliente = ClienteSimpleProperty.converterParaEntidade(
                Objects.requireNonNull(obs.stream()
                        .filter(c -> c.getNomeCliente().equals(cbPessoa.getValue()))
                        .findFirst()
                        .orElse(null)));

        LocalDateTime dataEmissao = dtEmissao.getValue().atTime(00,00);
        List<Produto> itens = tbItens.getItems().stream()
                .map(ProdutosSimpleProperty::converterParaEntidade)
                .collect(Collectors.toList());
        String subTotal = ExtracaoDeDados.textFieldToString(txtSubTotal);
        String total = ExtracaoDeDados.textFieldToString(txtTotal);
        Date dataAtualizada = new Date();
        try {
            dataAtualizada = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                    .parse(dataEmissao.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Movimento movimento = new Movimento(
                    null,
                    (double) itens.size(),
                    dataAtualizada,
                    itens,
                    cliente);

        boolean sucesso = movimentoService.cadastrar(movimento);
        if (sucesso) {
            fecharEAtualizarTabela();
        } else {
            AlertsUtil.alertaSimples(
                    "Erro de gravação",
                    "Não foi possivel criar o movimento.",
                    Alert.AlertType.ERROR);
            cbPessoa.requestFocus();
        }
    }

//    private void alterarMovimento() {
//        String descricao = ExtracaoDeDados.textFieldToString(txtDescricaoProduto);
//        int qtEstoque = ExtracaoDeDados.textFieldToInt(txtQtProduto);
//        double valorVenda = ExtracaoDeDados.textFieldToDouble(txtValorProduto);
//        Long id = ExtracaoDeDados.textFieldToLong(txtCodProduto);
//        Produto produto = new Produto(null, descricao, valorVenda, qtEstoque, null);
//
//        boolean sucesso = produtoService.alterar(produto, id);
//        if (sucesso) {
//            fecherEAtualizarTabela();
//        } else {
//            AlertsUtil.alertaSimples(
//                    "Erro de alteração",
//                    "Não foi possivel fazer a alteração do produto.",
//                    Alert.AlertType.ERROR);
//            txtDescricaoProduto.requestFocus();
//        }
//    }

    @FXML
    private ObservableList<ProdutosSimpleProperty> obterProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        List<ProdutosSimpleProperty> produtosSimpleProperties =
                produtos.stream()
                        .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                        .collect(Collectors.toList());
        return FXCollections.observableArrayList(produtosSimpleProperties);
    }

    private void exibirDadosDeProdutos() {
        Movimento movimento = movimentoApplication.getMovimento();
        if (Objects.nonNull(movimento)) {
            txtCodigoMovimento.setText(String.valueOf(movimento.getId()));

            cbPessoa.getItems().addAll(
                    clienteService.listarTodos().stream()
                            .map(ClienteSimpleProperty::converterParaSimpleProperty)
                            .collect(Collectors.toList()));
            cbPessoa.setValue(cbPessoa.getItems().get(0));

            dtEmissao.setChronology(LocalDate.now().getChronology());
            dtEmissao.setValue(LocalDate.now());
            ObservableList<ProdutosSimpleProperty> obsProdutos = obterProdutos();
            cbDescricao.setItems(obsProdutos);

            fabricarColTableProdutos(
                    (ObservableList<ProdutosSimpleProperty>)
                            itens.stream()
                            .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                            .collect(Collectors.toList()));

            txtSubTotal.setText(obterTotalItens(obsProdutos));
            txtDescAcres.setText("0.00");
            txtTotal.setText(obterTotalItens(obsProdutos));

            lblTituloCadastroMovimento.setText("Editar movimento");
            txtCodigoProduto.requestFocus();
        } else {
            txtCodigoMovimento.setText("");
            cbPessoa.getItems().addAll(
                    clienteService.listarTodos().stream()
                            .map(ClienteSimpleProperty::converterParaSimpleProperty)
                            .collect(Collectors.toList()));
            cbPessoa.setValue(cbPessoa.getItems().get(0));
            dtEmissao.setChronology(LocalDate.now().getChronology());
            dtEmissao.setValue(LocalDate.now());
            ObservableList<ProdutosSimpleProperty> obsProdutos = obterProdutos();
            cbDescricao.setItems(obsProdutos);

            fabricarColTableProdutos(FXCollections.observableArrayList());
            txtSubTotal.setText("0.00");
            txtValorUnitProduto.setText("0.00");
            txtQtdeProduto.setText("1");
            txtTotal.setText("0.00");
            lblTituloCadastroMovimento.setText("Novo produto");
            txtCodigoProduto.requestFocus();
        }
    }

    public void fabricarColTableProdutos(ObservableList<ProdutosSimpleProperty> produtos) {
        colCodigo.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colDescricao.setCellValueFactory(p -> p.getValue().nomeProdutoProperty());
        colQtde.setCellValueFactory(p -> p.getValue().estoqueProperty().asObject());
        colValor.setCellValueFactory(p -> p.getValue().valorProdutoProperty());
        tbItens.setItems(produtos);

        TabelaUtil.initButtons(
                colEditar,
                15,
                Constantes.CAMINHO_ICONE_EDIT,
                "icone-edit",
                (ProdutosSimpleProperty produto, ActionEvent event) -> {
                    scrollPanePrincipal.setVvalue(0.29);
                    txtCodigoProduto.setText(String.valueOf(produto.getId()));
                    cbDescricao.setItems(produtos);
                    cbDescricao.setValue(produtos.get(0));
                    txtQtdeProduto.setText(String.valueOf(produto.getEstoque()));
                    txtValorUnitProduto.setText(produto.getValorProduto());
                    txtCodigoProduto.requestFocus();
                });

        TabelaUtil.initButtons(
                colDelete,
                15,
                Constantes.CAMINHO_ICONE_DELETE,
                "icone-delete",
                (ProdutosSimpleProperty produto, ActionEvent event) -> {
                    boolean sucesso = itens.remove(produto);
                    if (sucesso) {
                   fabricarColTableProdutos(
                                (ObservableList<ProdutosSimpleProperty>)
                                        itens.stream()
                                                .map(ProdutosSimpleProperty::converterParaSimpleProperty)
                                                .collect(Collectors.toList()));
                    }
                });

        txtSubTotal.setText(obterTotalItens(produtos));
        txtTotal.setText(obterTotalItens(produtos));
    }

    private void fecharEAtualizarTabela() {
        Stage stage = (Stage) btnSalvarMovimento.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        fecharCadastroMovimento();
    }

    @FXML
    private void fecharCadastroMovimento() {
        MovimentoCadastroApplication.getStage().close();
    }

    public String obterTotalItens(ObservableList<ProdutosSimpleProperty> produtos){
        return String.valueOf(produtos.stream()
                        .map(ProdutosSimpleProperty::getValorProdutoSemMascara)
                        .mapToDouble(Double::parseDouble)
                        .sum());
    }

    public int obterQuantidadeItens(ObservableList<ProdutosSimpleProperty> produtos){
        return produtos.size();
    }

    private void habilitarPesquisaPorTeclaEnter(){
        txtCodigoProduto.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
               buscarItem();
            }
        });

        txtQtdeProduto.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.ENTER)){
               adicionarItem();
            }
        });
    }


}
