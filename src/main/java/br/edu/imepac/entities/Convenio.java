package br.edu.imepac.entities;

import java.util.Objects;

public class Convenio {
    private Integer id;
    private String nome;
    private String descricao;

    public Convenio() {
    }

    public Convenio(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convenio convenio = (Convenio) o;
        return id.equals(convenio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
