package dev.cepex.Cepex.Repository;

import dev.cepex.Cepex.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByRa(String ra);
    Optional<Professor> findByEmail(String email);

}