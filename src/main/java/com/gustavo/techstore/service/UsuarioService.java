package com.gustavo.techstore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gustavo.techstore.dto.UsuarioRequest;
import com.gustavo.techstore.dto.UsuarioResponse;
import com.gustavo.techstore.entity.Usuario;
import com.gustavo.techstore.exception.EmailJaCadastradoException;
import com.gustavo.techstore.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> listarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::paraResponse)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return paraResponse(usuario);
    }

    public UsuarioResponse criar(UsuarioRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailJaCadastradoException(request.getEmail());
        }

        Usuario usuario = new Usuario();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());

        usuario.setSenha(senhaCriptografada);
        usuario.setDataCadastro(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return paraResponse(usuarioSalvo);
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuarioExistente.getEmail().equals(request.getEmail())
                && usuarioRepository.existsByEmail(request.getEmail())) {

            throw new EmailJaCadastradoException(request.getEmail());
        }

        usuarioExistente.setNome(request.getNome());
        usuarioExistente.setEmail(request.getEmail());

        String senhaCriptografada = passwordEncoder.encode(request.getSenha());

        usuarioExistente.setSenha(senhaCriptografada);

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);

        return paraResponse(usuarioAtualizado);
    }

    public void deletar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    private UsuarioResponse paraResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro());
    }
}