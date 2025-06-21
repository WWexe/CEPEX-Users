package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Model.Perfil;
import dev.cepex.Cepex.Repository.PerfilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @Transactional
    public Perfil salvarPerfil(Perfil perfil) {
        if (perfil == null || !StringUtils.hasText(perfil.getNome())) {
            throw new IllegalArgumentException("O nome do perfil não pode ser nulo ou vazio.");
        }

        if (perfil.getId() == null) {
            Optional<Perfil> perfilExistente = perfilRepository.findByNome(perfil.getNome());
            if (perfilExistente.isPresent()) {
                throw new IllegalArgumentException("Já existe um perfil com o nome: " + perfil.getNome());
            }
        } else {
            Optional<Perfil> perfilComMesmoNome = perfilRepository.findByNome(perfil.getNome());
            if (perfilComMesmoNome.isPresent() && !perfilComMesmoNome.get().getId().equals(perfil.getId())) {
                throw new IllegalArgumentException("O nome '" + perfil.getNome() + "' já está em uso por outro perfil.");
            }
            if (!perfilRepository.existsById(perfil.getId())) {
                throw new EntityNotFoundException("Perfil com ID " + perfil.getId() + " não encontrado para atualização.");
            }
        }
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listarTodosPerfis() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> buscarPerfilPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public Optional<Perfil> buscarPerfilPorNome(String nome) {
        if (!StringUtils.hasText(nome)) {
            return Optional.empty();
        }
        return perfilRepository.findByNome(nome);
    }

    @Transactional
    public void excluirPerfil(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw new EntityNotFoundException("Perfil com ID " + id + " não encontrado para exclusão.");
        }
        perfilRepository.deleteById(id);
    }

    @Transactional
    public Perfil atualizarPerfil(Long id, Perfil perfilDetalhes) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Perfil com ID " + id + " não encontrado para atualização."));

        if (perfilDetalhes == null || !StringUtils.hasText(perfilDetalhes.getNome())) {
            throw new IllegalArgumentException("O nome do perfil não pode ser nulo ou vazio para atualização.");
        }

        if (!perfilExistente.getNome().equalsIgnoreCase(perfilDetalhes.getNome())) {
            Optional<Perfil> conflitoDeNome = perfilRepository.findByNome(perfilDetalhes.getNome());
            if (conflitoDeNome.isPresent() && !conflitoDeNome.get().getId().equals(id)) {
                throw new IllegalArgumentException("O nome '" + perfilDetalhes.getNome() + "' já está em uso por outro perfil.");
            }
            perfilExistente.setNome(perfilDetalhes.getNome());
        }

        return perfilRepository.save(perfilExistente);
    }
}