package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Model.Usuario;
import dev.cepex.Cepex.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return;
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuarioComNovosDados) {
        Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findById(id);

        if (usuarioExistenteOpt.isPresent()) {
            Usuario usuarioExistente = usuarioExistenteOpt.get();

            if (usuarioComNovosDados.getFirstname() != null) {
                usuarioExistente.setFirstname(usuarioComNovosDados.getFirstname());
            }
            if (usuarioComNovosDados.getEmail() != null) {
                usuarioExistente.setEmail(usuarioComNovosDados.getEmail());
            }
            if (usuarioComNovosDados.getSenha() != null && !usuarioComNovosDados.getSenha().isEmpty()) {
                usuarioExistente.setSenha(usuarioComNovosDados.getSenha());
            }

            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
    }
}