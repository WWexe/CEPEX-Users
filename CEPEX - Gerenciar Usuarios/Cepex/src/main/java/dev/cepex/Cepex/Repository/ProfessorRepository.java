package dev.cepex.Cepex.Repository;

import dev.cepex.Cepex.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Opcional para JpaRepository, mas boa prática

import java.util.Optional; // Para métodos que podem não retornar um resultado

@Repository // Indica ao Spring que esta é uma interface de repositório
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByRa(String ra);
    Optional<Professor> findByEmail(String email);

}