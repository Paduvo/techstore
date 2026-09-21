package com.gustavo.techstore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.gustavo.techstore.entity.StatusPedido;

public class PedidoResponse {

    private Long id;
    private Long usuarioId;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private List<ItemPedidoResponse> itens;

    public PedidoResponse(
            Long id,
            Long usuarioId,
            LocalDateTime data,
            BigDecimal valorTotal,
            StatusPedido status,
            List<ItemPedidoResponse> itens) {

        this.id = id;
        this.usuarioId = usuarioId;
        this.data = data;
        this.valorTotal = valorTotal;
        this.status = status;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedidoResponse> getItens() {
        return itens;
    }
}