package dev.cepex.Cepex.Model; // Ou seu pacote de modelo

import jakarta.persistence.*;
import java.util.Objects;
// import java.util.Set; // Se você precisar da relação inversa

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome; // Ex: "ROLE_COORDENADOR", "ROLE_PROFESSOR", "ROLE_ALUNO"

    public Perfil() {}

    public Perfil(String nome) {
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    // public Set<Usuario> getUsuarios() { return usuarios; }
    // public void setUsuarios(Set<Usuario> usuarios) { this.usuarios = usuarios; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id) && Objects.equals(nome, perfil.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Perfil{id=" + id + ", nome='" + nome + "'}";
    }
}