package com.gustavo.techstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.techstore.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioEmail(String email);
}