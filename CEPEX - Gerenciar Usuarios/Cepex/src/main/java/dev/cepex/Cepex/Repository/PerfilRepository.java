package dev.cepex.Cepex.Repository; // Ou seu pacote de repositório

import dev.cepex.Cepex.Model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String nome);
}