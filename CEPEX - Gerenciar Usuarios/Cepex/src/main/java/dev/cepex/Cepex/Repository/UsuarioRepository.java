package dev.cepex.Cepex.Repository;

import dev.cepex.Cepex.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Aluno, Long> {
}