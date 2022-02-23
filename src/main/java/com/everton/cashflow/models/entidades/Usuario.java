package com.everton.cashflow.models.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Usuario {
    private Long id;
    private String login;
    private String senha;
    private String nome;
}
