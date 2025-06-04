package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Model.Usuario;
import dev.cepex.Cepex.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> listarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}