package com.gustavo.techstore.dto;

import java.math.BigDecimal;

public class ItemPedidoResponse {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemPedidoResponse(
            Long id,
            Long produtoId,
            String produtoNome,
            Integer quantidade,
            BigDecimal precoUnitario) {

        this.id = id;
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;

        this.subtotal = precoUnitario.multiply(
                BigDecimal.valueOf(quantidade));
    }

    public Long getId() {
        return id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}