package com.everton.cashflow.controller;

import com.everton.cashflow.main.ProdutoApplication;
import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.entidades.Produto;
import com.everton.cashflow.models.services.ProdutoService;
import com.everton.cashflow.util.AlertsUtil;
import com.everton.cashflow.util.ExtracaoDeDados;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {

    @FXML
    private Button btnCancelarProduto;

    @FXML
    private Button btnSalvarProduto;

    @FXML
    private TextField txtCodProduto;

    @FXML
    private TextField txtDescricaoProduto;

    @FXML
    private TextField txtQtProduto;

    @FXML
    private TextField txtValorProduto;

    private  ProdutoService produtoService = ProdutoService.getInstance();
    private  ProdutoApplication produtoApplication = ProdutoApplication.getInstance();
    private CadastroController cadastroController = CadastroController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exibirDadosDeCliente();
        txtDescricaoProduto.requestFocus();
    }

    @FXML
    private void SalvarOuAtualizar(){
        Produto produto = produtoApplication.getProduto();
        if (Objects.isNull(produto)){
            cadastrarProduto();
        }else{
            alterarProduto();
        }
    }

    private void cadastrarProduto(){
        String descricao = ExtracaoDeDados.textFieldToString(txtDescricaoProduto);
        int qtEstoque = ExtracaoDeDados.textFieldToInt(txtQtProduto);
        double valorVenda = ExtracaoDeDados.textFieldToDouble(txtValorProduto);
        Produto produto = new Produto(null, descricao, valorVenda, qtEstoque, null);

        boolean sucesso = produtoService.cadastrar(produto);
        if(sucesso){
            fecherEAtualizarTabela();
        }else {
            AlertsUtil.alertaSimples(
                    "Erro de gravação",
                    "Não foi possivel fazer a inclusão do produto.",
                    Alert.AlertType.ERROR);
            txtDescricaoProduto.requestFocus();
        }
    }

    private void alterarProduto(){
        String descricao = ExtracaoDeDados.textFieldToString(txtDescricaoProduto);
        int qtEstoque = ExtracaoDeDados.textFieldToInt(txtQtProduto);
        double valorVenda = ExtracaoDeDados.textFieldToDouble(txtValorProduto);
        Long id = ExtracaoDeDados.textFieldToLong(txtCodProduto);
        Produto produto = new Produto(null, descricao,valorVenda, qtEstoque, null);

        boolean sucesso = produtoService.alterar(produto, id);
        if(sucesso){
            fecherEAtualizarTabela();
        }else {
            AlertsUtil.alertaSimples(
                    "Erro de alteração",
                    "Não foi possivel fazer a alteração do produto.",
                    Alert.AlertType.ERROR);
            txtDescricaoProduto.requestFocus();
        }
    }

    private void exibirDadosDeCliente(){
        Produto produto = produtoApplication.getProduto();
        if (Objects.nonNull(produto)){
            txtCodProduto.setText(String.valueOf(produto.getId()));
            txtDescricaoProduto.setText(produto.getNomeProduto());
            txtQtProduto.setText(String.valueOf(produto.getEstoque()));
            txtValorProduto.setText(String.valueOf(produto.getValorProduto()));
            txtDescricaoProduto.selectEndOfNextWord();
            txtDescricaoProduto.requestFocus();
        }
    }

    private void fecherEAtualizarTabela(){
    Stage stage = (Stage) btnSalvarProduto.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        fecharCadastroProduto();
    }

    @FXML
    private void fecharCadastroProduto(){
        ProdutoApplication.getStage().close();
    }


}
