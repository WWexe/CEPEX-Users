package dev.cepex.Cepex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Objects; // Para hashCode e equals
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "O primeiro nome é obrigatório")
    @Size(min = 2, max = 100, message = "O primeiro nome deve ter entre 2 e 100 caracteres")
    private String firstname;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(min = 2, max = 100, message = "O sobrenome deve ter entre 2 e 100 caracteres")
    private String lastname;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O RA é obrigatório")
    @Size(min = 8, max = 8, message = "RA Inválido")
    private String ra;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(
                    name = "user_user_id",
                    referencedColumnName = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_permission_id",
                    referencedColumnName = "permission_id" // Nome da PK na sua tabela de definições de Permission (ex: "permission_id" ou "id").
                    // Ajuste conforme a PK da sua tabela de definições de Permission.
            )
    )
    private Set<Perfil> perfis = new HashSet<>(); // LINHA CORRIGIDA

    public Usuario() {
    }

    public Usuario(String firstname, String lastname, String email, String ra, String cpf, String senha) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.ra = ra;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; } // Este setter receberá a senha com HASH da camada de serviço
    public Set<Perfil> getPerfis() { return perfis; }
    public void setPerfis(Set<Perfil> perfis) { this.perfis = perfis; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", ra='" + ra + '\'' +
                ", cpf='" + cpf + '\'' +
                ", perfis=" + (perfis != null ? perfis.stream()
                .map(perfil -> perfil.getNome()) // Assumindo que Perfil tem um método getNome()
                .collect(Collectors.joining(", ")) : "Nenhum") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || !(o instanceof Usuario)) return false; // Ajuste para herança se necessário
        Usuario usuario = (Usuario) o;
        if (id != null && usuario.id != null) {
            return Objects.equals(id, usuario.id);
        }
        return Objects.equals(email, usuario.email) || Objects.equals(cpf, usuario.cpf);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hash(id);
        }
        return Objects.hash(email, cpf); // Ou apenas um deles se for garantidamente único e não nulo
    }
}