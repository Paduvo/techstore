package com.gustavo.techstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.techstore.dto.PedidoRequest;
import com.gustavo.techstore.dto.PedidoResponse;
import com.gustavo.techstore.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criar(
            @Valid @RequestBody PedidoRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return pedidoService.criar(request, email);
    }

    @GetMapping
    public List<PedidoResponse> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public PedidoResponse buscarPorId(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        return pedidoService.buscarPorId(id, email);
    }

    @GetMapping("/meus-pedidos")
    public List<PedidoResponse> listarMeusPedidos(
            Authentication authentication) {

        String email = authentication.getName();

        return pedidoService.listarMeusPedidos(email);
    }
}