package com.gustavo.techstore.dto;

import java.time.LocalDateTime;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;

    public UsuarioResponse(
            Long id,
            String nome,
            String email,
            LocalDateTime dataCadastro) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}