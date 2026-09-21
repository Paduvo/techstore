package com.gustavo.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.techstore.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}