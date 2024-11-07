package br.edu.imepac.administrativo.entidades;

import java.util.Objects;

public class Medico {

    private long id;
    private String crm;
    private String nome;
    private String email;
    private String commitTest;

    public Medico() {
    }

    public Medico(long id, String crm, String nome, String email) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return id == medico.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}