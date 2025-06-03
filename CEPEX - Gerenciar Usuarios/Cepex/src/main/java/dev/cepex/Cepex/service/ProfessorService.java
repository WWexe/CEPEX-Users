package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Model.Professor;
import dev.cepex.Cepex.Model.Perfil; // Importar Perfil
import dev.cepex.Cepex.Repository.ProfessorRepository;
import dev.cepex.Cepex.Repository.PerfilRepository; // Importar PerfilRepository
// Removidos os imports de Permission e UserPermissionRepository, pois usaremos Perfil
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Outros imports do seu UsuarioService (Page, Pageable, Specification, etc.)

@Service
public class ProfessorService extends UsuarioService { // Assume que UsuarioService existe e é estendido

    @Autowired
    private ProfessorRepository professorRepository;

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
            professor.removePerfil(perfilCoordenador); // Usa o método helper de Usuario.java
        }

        // 3. Salva o professor (o método base deve lidar com hash de senha, etc.)
        // A chamada ao método save do repositório (dentro de salvarUsuarioBase ou diretamente)
        // irá persistir as mudanças na coleção 'perfis', atualizando a tabela 'user_permission'.
        Professor professorSalvo;
        if (super.salvarUsuarioBase != null) { // Verifica se o método da superclasse está disponível
            professorSalvo = (Professor) super.salvarUsuarioBase(professor);
        } else {
            professorSalvo = professorRepository.save(professor);
        }

        return professorSalvo;
    }

    // Outros métodos de ProfessorService (buscarProfessorPorId, listarTodosProfessores, etc.)
    // ...
}
