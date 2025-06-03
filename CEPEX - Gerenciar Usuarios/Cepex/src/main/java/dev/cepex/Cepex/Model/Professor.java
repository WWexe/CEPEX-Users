package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
// Não é necessário importar dev.cepex.Cepex.Model.TipoProfessor se estiver no mesmo pacote,
// mas se estivesse em outro, seria: import dev.cepex.Cepex.Model.OutroPacote.TipoProfessor;

@Entity
@Table(name = "professores")
public class Professor extends Usuario {

    @NotBlank(message = "O departamento é obrigatório")
    @Column(name = "departamento")
    private String departamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TipoProfessor tipo; // << AGORA este campo usará o enum do arquivo TipoProfessor.java

    @Column(name = "titulacao")
    private String titulacao;

    public Professor() {
        super();
    }

    public Professor(String firstname, String lastname, String email, String ra, String cpf, String senha,
                     String departamento, TipoProfessor tipo, String titulacao) {
        super(firstname, lastname, email, ra, cpf, senha);
        this.departamento = departamento;
        this.tipo = tipo; // 'tipo' aqui será dev.cepex.Cepex.Model.TipoProfessor
        this.titulacao = titulacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public TipoProfessor getTipo() { // Remova o getter duplicado/incompleto
        return tipo;
    }

    public void setTipo(TipoProfessor tipo) { // 'tipo' aqui será dev.cepex.Cepex.Model.TipoProfessor
        this.tipo = tipo;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public boolean isCoordenador() {
        // Esta lógica funcionará corretamente com o enum externo
        return this.tipo == dev.cepex.Cepex.Model.TipoProfessor.COORDENADOR;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "usuario=" + super.toString() +
                ", departamento='" + departamento + '\'' +
                ", tipo=" + (tipo != null ? tipo.name() : "null") +
                ", titulacao='" + titulacao + '\'' +
                '}';
    }
}