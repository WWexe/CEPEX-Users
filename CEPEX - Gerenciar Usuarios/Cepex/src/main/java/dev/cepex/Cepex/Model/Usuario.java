package dev.cepex.Cepex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Objects; // Para hashCode e equals
import java.util.Set;
import java.util.stream.Collectors; // Para o toString dos perfis
// import java.time.LocalDate; // Se for usar campos de data

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
// Se usar SINGLE_TABLE, adicione: @DiscriminatorColumn(name = "TIPO_USUARIO", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario { // Classe abstrata se não houver "Usuários" genéricos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // ADICIONADO para mapear para a coluna user_id
    private Long id;

    @NotBlank(message = "O primeiro nome é obrigatório")
    @Size(min = 2, max = 100, message = "O primeiro nome deve ter entre 2 e 100 caracteres")
    private String firstname;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(min = 2, max = 100, message = "O sobrenome deve ter entre 2 e 100 caracteres")
    private String lastname;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Column(unique = true) // Garante que o email seja único no banco de dados
    private String email;

    // RA pode ser opcional ou específico para certos tipos de usuário (ex: Aluno)
    // Se for obrigatório para todos, adicione @NotBlank
    private String ra;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true) // Garante que o CPF seja único no banco de dados
    // Adicionar validação de formato de CPF (ex: @Pattern ou uma validação customizada)
    private String cpf;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    // Lembre-se: A senha deve ser armazenada com HASH no banco.
    // A validação de complexidade (letras maiúsculas, números, símbolos)
    // geralmente é feita na camada de serviço antes de fazer o hash.
    private String senha;

    // Exemplo de outros campos comuns que você pode ter:
    // private LocalDate dataNascimento;
    // private boolean ativo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission", // << CORRIGIDO para o nome exato da sua tabela de junção
            joinColumns = @JoinColumn(
                    name = "user_user_id",
                    referencedColumnName = "user_id" // Aponta para a PK de Usuario
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_permission_id", // Coluna em user_permission que aponta para Permission
                    // Confirme se 'permission_permission_id' é o nome correto da FK para Permission
                    referencedColumnName = "permission_id" // Nome da PK na sua tabela de definições de Permission (ex: "permission_id" ou "id").
                    // Ajuste conforme a PK da sua tabela de definições de Permission.
            )
    )
    private Set<Perfil> perfis = new HashSet<>(); // LINHA CORRIGIDA

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Usuario() {
    }

    /**
     * Construtor com os campos principais.
     */
    public Usuario(String firstname, String lastname, String email, String ra, String cpf, String senha) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.ra = ra;
        this.cpf = cpf;
        this.senha = senha; // A senha recebida aqui deve ser a senha já com hash se vinda do serviço, ou a senha crua a ser processada.
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

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil);
        // Se Perfil tivesse uma coleção de Usuarios e você quisesse manter bidirecional:
        // perfil.getUsuarios().add(this); // Supondo que Perfil tem getUsuarios()
    }

    public void removePerfil(Perfil perfil) {
        this.perfis.remove(perfil);
        // Se Perfil tivesse uma coleção de Usuarios e você quisesse manter bidirecional:
        // perfil.getUsuarios().remove(this); // Supondo que Perfil tem getUsuarios()
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", ra='" + ra + '\'' +
                ", cpf='" + cpf + '\'' +
                // NUNCA inclua a senha no toString por questões de segurança!
                ", perfis=" + (perfis != null ? perfis.stream()
                .map(perfil -> perfil.getNome()) // Assumindo que Perfil tem um método getNome()
                .collect(Collectors.joining(", ")) : "Nenhum") +
                '}';
    }

    /**
     * É crucial implementar equals e hashCode corretamente se você for
     * armazenar instâncias de Usuario em coleções como Set ou Map,
     * ou para certas operações do JPA.
     *
     * Uma abordagem comum é usar um campo de negócio único (como CPF ou email)
     * ou o ID após a persistência.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || !(o instanceof Usuario)) return false; // Ajuste para herança se necessário
        Usuario usuario = (Usuario) o;
        // Se o ID ainda não foi gerado (nova entidade), compare por um campo de negócio único.
        // Se o ID já existe, ele é o melhor candidato para a igualdade.
        if (id != null && usuario.id != null) {
            return Objects.equals(id, usuario.id);
        }
        // Fallback para campos de negócio se os IDs não estiverem disponíveis ou se preferir
        // (cuidado com a mutabilidade desses campos e a consistência com hashCode)
        return Objects.equals(email, usuario.email) || Objects.equals(cpf, usuario.cpf);
    }

    @Override
    public int hashCode() {
        // Se o ID estiver disponível, use-o.
        // Caso contrário, use um campo de negócio único.
        // A consistência entre equals e hashCode é vital.
        if (id != null) {
            return Objects.hash(id);
        }
        return Objects.hash(email, cpf); // Ou apenas um deles se for garantidamente único e não nulo
    }
}