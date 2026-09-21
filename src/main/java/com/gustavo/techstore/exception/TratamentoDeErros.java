package com.gustavo.techstore.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, Object> tratarErrosDeValidacao(
                        MethodArgumentNotValidException exception) {

                Map<String, String> campos = new HashMap<>();

                exception.getBindingResult()
                                .getFieldErrors()
                                .forEach(erro -> campos.put(
                                                erro.getField(),
                                                erro.getDefaultMessage()));

                Map<String, Object> resposta = new HashMap<>();

                resposta.put("erro", "Dados inválidos");
                resposta.put("campos", campos);

                return resposta;
        }

        @ExceptionHandler(ProdutoNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public Map<String, String> tratarProdutoNaoEncontrado(
                        ProdutoNaoEncontradoException exception) {

                return Map.of(
                                "erro", "Produto não encontrado",
                                "mensagem", exception.getMessage());
        }

        @ExceptionHandler(UsuarioNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public Map<String, String> tratarUsuarioNaoEncontrado(
                        UsuarioNaoEncontradoException exception) {

                return Map.of(
                                "erro", "Usuário não encontrado",
                                "mensagem", exception.getMessage());
        }

        @ExceptionHandler(EstoqueInsuficienteException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, String> tratarEstoqueInsuficiente(
                        EstoqueInsuficienteException exception) {

                return Map.of(
                                "erro", "Estoque insuficiente",
                                "mensagem", exception.getMessage());
        }

        @ExceptionHandler(EmailJaCadastradoException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public Map<String, String> tratarEmailJaCadastrado(
                        EmailJaCadastradoException exception) {

                return Map.of(
                                "erro", "Email já cadastrado",
                                "mensagem", exception.getMessage());
        }

        @ExceptionHandler(CredenciaisInvalidasException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public Map<String, String> tratarCredenciaisInvalidas(
                        CredenciaisInvalidasException exception) {

                return Map.of(
                                "erro", "Não autorizado",
                                "mensagem", exception.getMessage());
        }
}