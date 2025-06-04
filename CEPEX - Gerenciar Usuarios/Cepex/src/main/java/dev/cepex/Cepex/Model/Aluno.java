package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
// No longer needs jakarta.persistence.Column for its own 'ra'

@Entity
@Table(name = "alunos")
public class Aluno extends Usuario {


    private String curso;

    public Aluno() {
        super();
    }


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}