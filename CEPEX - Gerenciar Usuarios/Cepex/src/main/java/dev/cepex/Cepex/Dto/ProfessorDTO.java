package dev.cepex.Cepex.Dto; // Pacote correto conforme você usou

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
// Não precisa de @NotNull para boolean primitivo, pois ele sempre terá um valor (false por padrão)

public class ProfessorDTO {

    @NotBlank(message = "O primeiro nome é obrigatório")
    @Size(min = 2, max = 100)
    private String firstname;

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(min = 2, max = 100)
    private String lastname;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    // REMOVA o enum interno e o campo 'tipo' anterior daqui
    // ADICIONE este campo:
    private boolean ehCoordenador; // Campo para o checkbox "É Coordenador?"

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    // Getters e Setters
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    // Getter e Setter para ehCoordenador
    public boolean isEhCoordenador() { return ehCoordenador; }
    public void setEhCoordenador(boolean ehCoordenador) { this.ehCoordenador = ehCoordenador; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    // Considere adicionar outros campos que vêm do formulário e são necessários para a entidade Professor,
    // como 'ra', 'departamento', 'titulacao', se aplicável.
    // private String ra;
    // private String departamento;
    // private String titulacao;
}