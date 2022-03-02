package com.everton.cashflow.controller;

import com.everton.cashflow.main.ClienteApplication;
import com.everton.cashflow.main.ProdutoApplication;
import com.everton.cashflow.models.entidades.Cliente;
import com.everton.cashflow.models.services.ClienteService;
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

public class ClienteController implements Initializable {
    @FXML private Button btnCancelarCliente;
    @FXML private Button btnSalvarCliente;
    @FXML private TextField txtCodCliente;
    @FXML private TextField txtNomeCliente;

    private ClienteService clienteService = ClienteService.getInstance();
    private ClienteApplication clienteApplication = ClienteApplication.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exibirDadosDeCliente();
        txtNomeCliente.requestFocus();
    }

    @FXML
    private void SalvarOuAtualizar(){
        Cliente cliente = clienteApplication.getCliente();
        if (Objects.isNull(cliente)){
            cadastrarCliente();
        }else{
            alterarCliente();
        }
    }

    private void cadastrarCliente(){
        String nome = ExtracaoDeDados.textFieldToString(txtNomeCliente);
        Cliente cliente = new Cliente(null, nome, null);
        boolean sucesso = clienteService.cadastrar(cliente);
        if(sucesso){
            fecherEAtualizarTabela();
        }else {
            AlertsUtil.alertaSimples(
                    "Erro de alteração",
                    "Não foi possivel fazer a inclusão do cliente.",
                    Alert.AlertType.ERROR);
            txtNomeCliente.requestFocus();
        }
    }

    private void alterarCliente(){
        String nome = ExtracaoDeDados.textFieldToString(txtNomeCliente);
        Long id = ExtracaoDeDados.textFieldToLong(txtCodCliente);
        Cliente cliente = new Cliente(null, nome, null);
        boolean sucesso = clienteService.alterar(cliente, id);
        if(sucesso){
            fecherEAtualizarTabela();
        }else {
            AlertsUtil.alertaSimples(
                    "Erro de alteração",
                    "Não foi possivel fazer a alteração do cliente.",
                    Alert.AlertType.ERROR);
            txtNomeCliente.requestFocus();
        }
    }

    private void exibirDadosDeCliente(){
        Cliente cliente = clienteApplication.getCliente();
        if (Objects.nonNull(cliente)){
            txtCodCliente.setText(String.valueOf(cliente.getId()));
            txtNomeCliente.setText(cliente.getNomeCliente());
            txtNomeCliente.selectEnd();
            txtNomeCliente.requestFocus();
        }else{
            txtCodCliente.setText("");
            txtNomeCliente.setText("");
            txtNomeCliente.requestFocus();
        }
    }

    private void fecherEAtualizarTabela(){
        Stage stage = (Stage) btnSalvarCliente.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        fecharCadastroCliente();
    }

    @FXML
    private void fecharCadastroCliente(){
        ClienteApplication.getStage().close();
    }

}
