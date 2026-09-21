package com.gustavo.techstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gustavo.techstore.entity.Produto;
import com.gustavo.techstore.exception.ProdutoNaoEncontradoException;
import com.gustavo.techstore.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {

        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setEstoque(produto.getEstoque());
        produtoExistente.setImagem(produto.getImagem());

        return produtoRepository.save(produtoExistente);
    }

    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}