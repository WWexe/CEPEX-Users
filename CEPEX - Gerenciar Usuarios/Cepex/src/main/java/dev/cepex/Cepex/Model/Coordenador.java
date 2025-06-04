package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "coordenadores")
public class Coordenador extends Usuario {

    @Column
    private String areaDeCoordenacao;

    public Coordenador() {
        super();
    }

    // Getters e Setters
    public String getAreaDeCoordenacao() {
        return areaDeCoordenacao;
    }

    public void setAreaDeCoordenacao(String areaDeCoordenacao) {
        this.areaDeCoordenacao = areaDeCoordenacao;
    }
}