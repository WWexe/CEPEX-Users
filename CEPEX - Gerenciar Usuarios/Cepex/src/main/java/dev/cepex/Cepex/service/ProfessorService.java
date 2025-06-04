package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Model.Aluno;
import dev.cepex.Cepex.Model.Professor;
import dev.cepex.Cepex.Model.Perfil; // Importar Perfil
import dev.cepex.Cepex.Repository.AlunoRepository;
import dev.cepex.Cepex.Repository.ProfessorRepository;
import dev.cepex.Cepex.Repository.PerfilRepository; // Importar PerfilRepository
// Removidos os imports de Permission e UserPermissionRepository, pois usaremos Perfil
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Outros imports do seu UsuarioService (Page, Pageable, Specification, etc.)

@Service
public class ProfessorService extends UsuarioService { // Assume que UsuarioService existe e é estendido

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listarTodos(){ return professorRepository.findAll(); }

    public Professor buscarPorId(Long id){ return professorRepository.findById(id).orElse(null); }

    public Professor salvar(Aluno aluno){ return professorRepository.save(aluno); }

    public void deletar(Long id){ professorRepository.deleteById(id); }

    @Autowired
    private PerfilRepository perfilRepository; // Injetar o PerfilRepository

    // Defina o nome exato do perfil de Coordenador como está no seu banco de dados
    private static final String COORDENADOR_PERFIL_NOME = "ROLE_COORDENADOR"; // Exemplo, ajuste conforme necessário

    @Transactional
    public Professor salvarProfessor(Professor professor) {
        // Lógica de validação específica do professor (ex: RA) pode vir aqui
        if (professor.getId() == null && professor.getRa() != null && professorRepository.findByRa(professor.getRa()).isPresent()) {
            throw new IllegalArgumentException("RA de professor já cadastrado: " + professor.getRa());
        }

        // 1. Busca o Perfil de Coordenador
        Perfil perfilCoordenador = perfilRepository.findByNome(COORDENADOR_PERFIL_NOME)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Perfil '" + COORDENADOR_PERFIL_NOME + "' não encontrado no sistema. " +
                                "Por favor, cadastre este perfil."
                ));

        // 2. Adiciona ou remove o perfil de coordenador com base na flag
        if (professor.isCoordenador()) {
            professor.addPerfil(perfilCoordenador); // Usa o método helper de Usuario.java
        } else {
            professor.removerPerfil(perfilCoordenador); // Usa o método helper de Usuario.java
        }

        Professor professorSalvo = (Professor) super.salvar(professor);

        return professorSalvo;
    }

}
