package com.gustavo.techstore.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gustavo.techstore.dto.LoginRequest;
import com.gustavo.techstore.dto.LoginResponse;
import com.gustavo.techstore.entity.Usuario;
import com.gustavo.techstore.exception.CredenciaisInvalidasException;
import com.gustavo.techstore.repository.UsuarioRepository;
import com.gustavo.techstore.security.JwtService;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(CredenciaisInvalidasException::new);

        boolean senhaValida = passwordEncoder.matches(
                request.getSenha(),
                usuario.getSenha());

        if (!senhaValida) {
            throw new CredenciaisInvalidasException();
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new LoginResponse(token);
    }
}