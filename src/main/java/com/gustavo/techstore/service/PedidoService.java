package com.gustavo.techstore.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.techstore.dto.ItemPedidoRequest;
import com.gustavo.techstore.dto.ItemPedidoResponse;
import com.gustavo.techstore.dto.PedidoRequest;
import com.gustavo.techstore.dto.PedidoResponse;
import com.gustavo.techstore.entity.ItemPedido;
import com.gustavo.techstore.entity.Pedido;
import com.gustavo.techstore.entity.Produto;
import com.gustavo.techstore.entity.StatusPedido;
import com.gustavo.techstore.entity.Usuario;
import com.gustavo.techstore.exception.EstoqueInsuficienteException;
import com.gustavo.techstore.exception.ProdutoNaoEncontradoException;
import com.gustavo.techstore.repository.PedidoRepository;
import com.gustavo.techstore.repository.ProdutoRepository;
import com.gustavo.techstore.repository.UsuarioRepository;

@Service
public class PedidoService {

        private final PedidoRepository pedidoRepository;
        private final UsuarioRepository usuarioRepository;
        private final ProdutoRepository produtoRepository;

        public PedidoService(
                        PedidoRepository pedidoRepository,
                        UsuarioRepository usuarioRepository,
                        ProdutoRepository produtoRepository) {

                this.pedidoRepository = pedidoRepository;
                this.usuarioRepository = usuarioRepository;
                this.produtoRepository = produtoRepository;
        }

        @Transactional
        public PedidoResponse criar(PedidoRequest request, String email) {

              Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException(
                "Usuário autenticado não encontrado"));

                Pedido pedido = new Pedido();

                pedido.setUsuario(usuario);
                pedido.setData(LocalDateTime.now());
                pedido.setStatus(StatusPedido.PENDENTE);

                BigDecimal valorTotal = BigDecimal.ZERO;

                List<ItemPedido> itens = new ArrayList<>();

                for (ItemPedidoRequest itemRequest : request.getItens()) {

                        Produto produto = produtoRepository
                                        .findById(itemRequest.getProdutoId())
                                        .orElseThrow(() -> new ProdutoNaoEncontradoException(
                                                        itemRequest.getProdutoId()));

                        if (produto.getEstoque() < itemRequest.getQuantidade()) {
                                throw new EstoqueInsuficienteException(
                                                produto.getNome());
                        }

                        ItemPedido item = new ItemPedido();

                        item.setPedido(pedido);
                        item.setProduto(produto);
                        item.setQuantidade(itemRequest.getQuantidade());

                        // Guarda o preço do produto no momento da compra
                        item.setPrecoUnitario(produto.getPreco());

                        BigDecimal subtotal = produto.getPreco()
                                        .multiply(
                                                        BigDecimal.valueOf(
                                                                        itemRequest.getQuantidade()));

                        valorTotal = valorTotal.add(subtotal);

                        produto.setEstoque(
                                        produto.getEstoque()
                                                        - itemRequest.getQuantidade());

                        itens.add(item);
                }

                pedido.setValorTotal(valorTotal);
                pedido.setItens(itens);

                Pedido pedidoSalvo = pedidoRepository.save(pedido);

                return paraResponse(pedidoSalvo);
        }

        @Transactional(readOnly = true)
        public List<PedidoResponse> listarTodos() {

                return pedidoRepository.findAll()
                                .stream()
                                .map(this::paraResponse)
                                .toList();
        }

        @Transactional(readOnly = true)
        public PedidoResponse buscarPorId(Long id, String email) {

                Pedido pedido = pedidoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Pedido não encontrado com id: " + id));

                if (!pedido.getUsuario().getEmail().equals(email)) {
                        throw new RuntimeException(
                                        "Pedido não encontrado com id: " + id);
                }

                return paraResponse(pedido);
        }

        @Transactional(readOnly = true)
        public List<PedidoResponse> listarMeusPedidos(String email) {

                return pedidoRepository.findByUsuarioEmail(email)
                                .stream()
                                .map(this::paraResponse)
                                .toList();
        }

        private PedidoResponse paraResponse(Pedido pedido) {

                List<ItemPedidoResponse> itens = pedido.getItens()
                                .stream()
                                .map(item -> new ItemPedidoResponse(
                                                item.getId(),
                                                item.getProduto().getId(),
                                                item.getProduto().getNome(),
                                                item.getQuantidade(),
                                                item.getPrecoUnitario()))
                                .toList();

                return new PedidoResponse(
                                pedido.getId(),
                                pedido.getUsuario().getId(),
                                pedido.getData(),
                                pedido.getValorTotal(),
                                pedido.getStatus(),
                                itens);
        }
}