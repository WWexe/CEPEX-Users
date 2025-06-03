package dev.cepex.Cepex.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
// Se Usuario usa SINGLE_TABLE:
// import jakarta.persistence.DiscriminatorValue;

@Entity
@Table(name = "coordenadores") // Nome da tabela específica para coordenadores (se houver)
// Se Usuario usa SINGLE_TABLE, adicione: @DiscriminatorValue("COORDENADOR")
public class Coordenador extends Usuario {

    // id, firstname, lastname, email, cpf, senha, perfis são herdados de Usuario

    @Column
    private String areaDeCoordenacao; // Exemplo de campo específico

    // Construtores
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