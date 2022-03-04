package com.everton.cashflow.models.enums;

import java.util.Arrays;

public enum TipoConta {
    RECEBER(0),
    PAGAR(1);

    private int tipo;

    TipoConta(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo(){
        return tipo;
    }

    public TipoConta getTipo(int tipo){
        return Arrays.stream(TipoConta.values())
                .filter( tipoConta -> tipo == tipoConta.getTipo())
                .findFirst()
                .orElse(null);
    }
}
