package com.everton.cashflow.models.constantes;

public class Constantes {
    /* Diversos */
    public static final String PROP_URL_BASE = "url.base";
    public static final String CONFIG_PROPERTIES = "configuracao.properties";
    public static final String URL_LOGO_ICON = "/imagens/icons/icone-logo.png";

    /* Endpoints */
    public static final String ENDPOINT_USUARIOS_LOGAR = "/usuarios/logar";
    public static final String ENDPOINT_USUARIOS = "/usuarios";
    public static final String ENDPOINT_PRODUTO = "/produtos";
    public static final String ENDPOINT_PRODUTO_PESQUISA = "/produtos/pesquisa";
    public static final String ENDPOINT_MOVIMENTO = "/movimentos";
    public static final String ENDPOINT_MOVIMENTO_PESQUISA = "/movimentos/pesquisa";
    public static final String ENDPOINT_FINANCEIRO_A_RECEBER = "/receber";
    public static final String ENDPOINT_FINANCEIRO_A_PAGAR = "/pagar";
    public static final String ENDPOINT_CLIENTE = "/clientes";
    public static final String ENDPOINT_CLIENTE_PESQUISA = "/clientes/pesquisa";
    public static final String ENDPOINT_CONTA = "/contas";

    /* Path das telas principais */
    public static final String CAMINHO_TELA_LOGIN = "/fxmls/login.fxml";
    public static final String CAMINHO_TELA_INDEX = "/fxmls/index.fxml";
    public static final String CAMINHO_TELA_CADASTRO_CLIENTES = "/fxmls/cadastro-cliente.fxml";
    public static final String CAMINHO_TELA_CADASTRO_PRODUTOS = "/fxmls/cadastro-produto.fxml";
    public static final String CAMINHO_TELA_CADASTRO_MOVIMENTO = "/fxmls/cadastro-movimento.fxml";

    /* Path das sub-telas */
    public static final String CAMINHO_TELA_SECTION_HOME = "/fxmls/section-home.fxml";
    public static final String CAMINHO_TELA_SECTION_MOVIMENTO = "/fxmls/section-movimento.fxml";
    public static final String CAMINHO_TELA_SECTION_FINANCEIRO = "/fxmls/section-financeiro.fxml";
    public static final String CAMINHO_TELA_SECTION_CADASTRO = "/fxmls/section-cadastro.fxml";

    /* Path de icones svg */
    public static final String CAMINHO_ICONE_EDIT = "M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z";
    public static final String CAMINHO_ICONE_DELETE = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
    public static final String CAMINHO_ICONE_IMPRIMIR = "M19 8H5c-1.66 0-3 1.34-3 3v6h4v4h12v-4h4v-6c0-1.66-1.34-3-3-3zm-3 11H8v-5h8v5zm3-7c-.55 0-1-.45-1-1s.45-1 1-1 1 .45 1 1-.45 1-1 1zm-1-9H6v4h12V3z";
}
