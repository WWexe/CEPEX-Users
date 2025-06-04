package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
// No longer needs jakarta.persistence.Column for its own 'ra'

@Entity
// Regarding @Table(name = "alunos"):
// If using InheritanceType.SINGLE_TABLE (default for abstract class hierarchies),
// this annotation might be ignored, as all users go into the 'usuarios' table.
// If using InheritanceType.JOINED, this is correct for the joined part of Aluno.
@Table(name = "alunos")
public class Aluno extends Usuario {

    // REMOVE the entire 'int ra' field declaration:
    // @Column(unique = true, nullable = false)
    // private int ra;

    private String curso;

    public Aluno() {
        super(); // Calls the constructor of the Usuario class
    }

    // REMOVE the setter for 'int ra':
    // public void setRa(int ra) {
    //     this.ra = ra;
    // }

    // getRa() and setRa(String ra) are inherited from Usuario.

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}