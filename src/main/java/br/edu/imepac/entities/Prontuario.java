package br.edu.imepac.entities;

import java.util.Objects;

public class Prontuario {
    private Integer id;

    /*receituario e exames setados como 'text', pesquisar depois se faz diferença usar string*/
    private String receituario;
    private String exames;
    private String observacao;

    public Prontuario() {
    }

    public Prontuario(Integer id, String receituario, String exames, String observacao) {
        this.id = id;
        this.receituario = receituario;
        this.exames = exames;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceituario() {
        return receituario;
    }

    public void setReceituario(String receituario) {
        this.receituario = receituario;
    }

    public String getExames() {
        return exames;
    }

    public void setExames(String exames) {
        this.exames = exames;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario that = (Prontuario) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
