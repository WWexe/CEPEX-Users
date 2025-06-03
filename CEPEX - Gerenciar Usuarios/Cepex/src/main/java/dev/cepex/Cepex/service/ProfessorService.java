package dev.cepex.Cepex.service;

import dev.cepex.Cepex.Dto.ProfessorDTO;
import dev.cepex.Cepex.Model.Professor;
import dev.cepex.Cepex.Model.TipoProfessor; // IMPORTANTE: Import para o Enum do seu pacote Model
import dev.cepex.Cepex.Repository.ProfessorRepository; // Sua interface de Repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository; // Instância injetada do seu repositório

    public Professor cadastrarProfessor(ProfessorDTO dto) {
        Professor professor = new Professor();

        // Mapear campos básicos do DTO para a entidade
        professor.setFirstname(dto.getFirstname());
        professor.setLastname(dto.getLastname());
        professor.setEmail(dto.getEmail());
        professor.setCpf(dto.getCpf());

        // Definir a senha (sem hashing, conforme solicitado para este exercício)
        // ATENÇÃO MUITO IMPORTANTE: Armazenar senhas em texto plano como abaixo
        // é ALTAMENTE INSEGURO e NUNCA deve ser feito em um ambiente de produção.
        // Em um sistema real, a senha DEVE ser "hashed" usando PasswordEncoder.
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            professor.setSenha(dto.getSenha()); // Senha está sendo salva em texto plano
        } else {
            // Se a senha for obrigatória, é melhor lançar uma exceção aqui.
            throw new IllegalArgumentException("A senha não pode ser vazia para cadastro.");
        }

        // Mapear outros campos se existirem no DTO e precisarem ser persistidos.
        // Certifique-se que ProfessorDTO tenha os getters correspondentes (ex: getRa(), getDepartamento(), getTitulacao())
        // e a entidade Professor tenha os setters.
        /*
        if (dto.getRa() != null) { // Exemplo
            professor.setRa(dto.getRa());
        }
        if (dto.getDepartamento() != null) { // Exemplo
            professor.setDepartamento(dto.getDepartamento());
        }
        if (dto.getTitulacao() != null) { // Exemplo
            professor.setTitulacao(dto.getTitulacao());
        }
        */


        // Definir o tipo do professor (Coordenador ou Professor regular)
        // Esta lógica assume que seu ProfessorDTO possui o método isEhCoordenador()
        if (dto.isEhCoordenador()) {
            professor.setTipo(TipoProfessor.COORDENADOR); // Usa o Model.TipoProfessor.COORDENADOR
        } else {
            professor.setTipo(TipoProfessor.PROFESSOR);  // Usa o Model.TipoProfessor.PROFESSOR
        }

        // Salvar a entidade Professor usando a instância injetada do repositório
        return professorRepository.save(professor); // CORRIGIDO: Chamada ao método na instância injetada
    }

    // Outros métodos do service (ex: atualizarProfessor, buscarProfessorPorId, listarProfessores, etc.)
}