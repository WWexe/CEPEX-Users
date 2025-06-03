package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
// Se Usuario usa SINGLE_TABLE:
// import jakarta.persistence.DiscriminatorValue;

@Entity
@Table(name = "alunos") // Nome da tabela específica para alunos (se JOINED ou TABLE_PER_CLASS)
// Se Usuario usa SINGLE_TABLE, adicione: @DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {


    @Column(unique = true, nullable = false) // RA geralmente é único e obrigatório
    private int ra;

    private String curso; // Considere criar uma entidade Curso se for mais complexo

    // Construtores (opcional, mas útil)
    public Aluno() {
        super(); // Chama o construtor da classe pai
    }

    // Getters e Setters para campos específicos de Aluno
    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}