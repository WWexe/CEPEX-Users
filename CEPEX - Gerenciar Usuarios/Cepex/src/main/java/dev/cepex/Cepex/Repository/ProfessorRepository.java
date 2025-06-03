package dev.cepex.Cepex.Repository;

import dev.cepex.Cepex.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Opcional para JpaRepository, mas boa prática

import java.util.Optional; // Para métodos que podem não retornar um resultado

@Repository // Indica ao Spring que esta é uma interface de repositório
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // JpaRepository<Professor, Long> significa:
    // - Esta interface gerenciará entidades do tipo 'Professor'.
    // - A chave primária da entidade 'Professor' é do tipo 'Long' (o 'id' herdado de Usuario).

    // Spring Data JPA fornecerá automaticamente a implementação para métodos como:
    // - save(Professor professor) -> que você estava tentando criar
    // - findById(Long id)
    // - findAll()
    // - deleteById(Long id)
    // - e muitos outros...

    // Você também pode adicionar métodos de consulta personalizados aqui.
    // Por exemplo, para buscar um professor pelo email:
    // Optional<Professor> findByEmail(String email);

    // Para buscar todos os professores de um departamento específico:
    // List<Professor> findByDepartamento(String departamento);
}