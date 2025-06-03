package dev.cepex.Cepex.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue; // Se SINGLE_TABLE
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "professores") // Necessário se JOINED ou TABLE_PER_CLASS
// @DiscriminatorValue("PROFESSOR") // Se SINGLE_TABLE
public class Professor extends Usuario {

   @Column(name = "ra_professor", unique = true) // Exemplo
    private String ra;

    private String departamento;*/

    @Column(name = "is_coordenador")
    private boolean coordenador = false; // Flag para coordenador

    // Construtores, Getters, Setters
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public boolean isCoordenador() { return coordenador; }
    public void setCoordenador(boolean coordenador) { this.coordenador = coordenador; }
}