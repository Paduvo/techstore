package com.gustavo.techstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.techstore.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}