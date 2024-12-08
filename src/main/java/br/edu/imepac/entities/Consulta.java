package br.edu.imepac.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Consulta {
    private int id;
    private LocalDateTime dataHorario;
    private String sintomas;
    private boolean eRetorno;
    private boolean estaAtiva;
    private Paciente paciente;
    private Prontuario prontuario;
    private Convenio convenio;
    //funcionario
    //fazer a relação de medico e atendente


    public Consulta() {
    }

    public Consulta(int id, LocalDateTime dataHorario, String sintomas, boolean eRetorno,
                    boolean estaAtiva, Paciente paciente, Convenio convenio , Prontuario prontuario) {
        this.id = id;
        this.dataHorario = dataHorario;
        this.sintomas = sintomas;
        this.eRetorno = eRetorno;
        this.estaAtiva = estaAtiva;
        this.paciente = paciente;
        this.convenio = convenio;
        this.prontuario = prontuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public boolean iseRetorno() {
        return eRetorno;
    }

    public void seteRetorno(boolean eRetorno) {
        this.eRetorno = eRetorno;
    }

    public boolean isEstaAtiva() {
        return estaAtiva;
    }

    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return id == consulta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
