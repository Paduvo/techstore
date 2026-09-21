package com.gustavo.techstore.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String produto) {
        super("Estoque insuficiente para o produto: " + produto);
    }
}