// Em dev.cepex.Cepex.Model.Perfil.java (adapte se o nome/pacote for diferente)
package dev.cepex.Cepex.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfis") // Ou o nome da sua tabela de perfis/roles
public class Perfil implements GrantedAuthority { // Implemente GrantedAuthority

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id") // Nome da sua PK na tabela de perfis
    private Long id;

    @Column(name = "nome", nullable = false, unique = true) // Ex: "ROLE_USER", "ROLE_ADMIN", "ROLE_PROFESSOR_COORDENADOR"
    private String nome;

    // Construtores, Getters e Setters

    public Perfil() {
    }

    public Perfil(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome; // Spring Security usará este método para obter o nome do papel/permissão
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    // Considere implementar equals e hashCode baseados no 'nome' ou 'id'
}