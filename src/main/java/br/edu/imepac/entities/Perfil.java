package br.edu.imepac.entities;

import java.util.Objects;

public class Perfil {
    private Integer id;
    private String nome;
    private boolean cadastrarFuncionario;
    private boolean lerFuncionario;
    private boolean atualizarFuncionario;
    private boolean deletarFuncionario;
    private boolean listarFuncionario;
    private boolean cadastrarPaciente;
    private boolean lerPaciente;
    private boolean atualizarPaciente;
    private boolean deletarPaciente;
    private boolean listarPaciente;
    private boolean cadastrarConsulta;
    private boolean lerConsulta;
    private boolean atualizarConsulta;
    private boolean deletarConsulta;
    private boolean listarConsulta;
    private boolean cadastrarEspecialidade;
    private boolean lerEspecialiade;
    private boolean atualizarEspecialidade;
    private boolean deletarEspecialidade;
    private boolean listarEspecialidade;
    private boolean cadastrarConvenio;
    private boolean lerConvenio;
    private boolean atualizarConvenio;
    private boolean deletarConvenio;
    private boolean listarConvenio;
    private boolean cadastrarProntuario;
    private boolean lerProntuario;
    private boolean atualizarProntuario;
    private boolean deletarProntuario;
    private boolean listarPronturario;

    public Perfil() {
    }

    public Perfil(Integer id, String nome, boolean cadastrarFuncionario, boolean lerFuncionario, boolean atualizarFuncionario, boolean deletarFuncionario, boolean listarFuncionario, boolean cadastrarPaciente, boolean lerPaciente, boolean atualizarPaciente,
                  boolean deletarPaciente, boolean listarPaciente, boolean cadastrarConsulta, boolean lerConsulta, boolean atualizarConsulta, boolean deletarConsulta, boolean listarConsulta, boolean cadastrarEspecialidade, boolean lerEspecialiade, boolean atualizarEspecialidade,
                  boolean deletarEspecialidade, boolean listarEspecialidade, boolean cadastrarConvenio, boolean lerConvenio, boolean atualizarConvenio, boolean deletarConvenio, boolean listarConvenio, boolean cadastrarProntuario, boolean lerProntuario, boolean atualizarProntuario,
                  boolean deletarProntuario, boolean listarPronturario) {
        this.id = id;
        this.nome = nome;
        this.cadastrarFuncionario = cadastrarFuncionario;
        this.lerFuncionario = lerFuncionario;
        this.atualizarFuncionario = atualizarFuncionario;
        this.deletarFuncionario = deletarFuncionario;
        this.listarFuncionario = listarFuncionario;
        this.cadastrarPaciente = cadastrarPaciente;
        this.lerPaciente = lerPaciente;
        this.atualizarPaciente = atualizarPaciente;
        this.deletarPaciente = deletarPaciente;
        this.listarPaciente = listarPaciente;
        this.cadastrarConsulta = cadastrarConsulta;
        this.lerConsulta = lerConsulta;
        this.atualizarConsulta = atualizarConsulta;
        this.deletarConsulta = deletarConsulta;
        this.listarConsulta = listarConsulta;
        this.cadastrarEspecialidade = cadastrarEspecialidade;
        this.lerEspecialiade = lerEspecialiade;
        this.atualizarEspecialidade = atualizarEspecialidade;
        this.deletarEspecialidade = deletarEspecialidade;
        this.listarEspecialidade = listarEspecialidade;
        this.cadastrarConvenio = cadastrarConvenio;
        this.lerConvenio = lerConvenio;
        this.atualizarConvenio = atualizarConvenio;
        this.deletarConvenio = deletarConvenio;
        this.listarConvenio = listarConvenio;
        this.cadastrarProntuario = cadastrarProntuario;
        this.lerProntuario = lerProntuario;
        this.atualizarProntuario = atualizarProntuario;
        this.deletarProntuario = deletarProntuario;
        this.listarPronturario = listarPronturario;
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

    public boolean isCadastrarFuncionario() {
        return cadastrarFuncionario;
    }

    public void setCadastrarFuncionario(boolean cadastrarFuncionario) {
        this.cadastrarFuncionario = cadastrarFuncionario;
    }

    public boolean isLerFuncionario() {
        return lerFuncionario;
    }

    public void setLerFuncionario(boolean lerFuncionario) {
        this.lerFuncionario = lerFuncionario;
    }

    public boolean isAtualizarFuncionario() {
        return atualizarFuncionario;
    }

    public void setAtualizarFuncionario(boolean atualizarFuncionario) {
        this.atualizarFuncionario = atualizarFuncionario;
    }

    public boolean isDeletarFuncionario() {
        return deletarFuncionario;
    }

    public void setDeletarFuncionario(boolean deletarFuncionario) {
        this.deletarFuncionario = deletarFuncionario;
    }

    public boolean isListarFuncionario() {
        return listarFuncionario;
    }

    public void setListarFuncionario(boolean listarFuncionario) {
        this.listarFuncionario = listarFuncionario;
    }

    public boolean isCadastrarPaciente() {
        return cadastrarPaciente;
    }

    public void setCadastrarPaciente(boolean cadastrarPaciente) {
        this.cadastrarPaciente = cadastrarPaciente;
    }

    public boolean isLerPaciente() {
        return lerPaciente;
    }

    public void setLerPaciente(boolean lerPaciente) {
        this.lerPaciente = lerPaciente;
    }

    public boolean isAtualizarPaciente() {
        return atualizarPaciente;
    }

    public void setAtualizarPaciente(boolean atualizarPaciente) {
        this.atualizarPaciente = atualizarPaciente;
    }

    public boolean isDeletarPaciente() {
        return deletarPaciente;
    }

    public void setDeletarPaciente(boolean deletarPaciente) {
        this.deletarPaciente = deletarPaciente;
    }

    public boolean isListarPaciente() {
        return listarPaciente;
    }

    public void setListarPaciente(boolean listarPaciente) {
        this.listarPaciente = listarPaciente;
    }

    public boolean isCadastrarConsulta() {
        return cadastrarConsulta;
    }

    public void setCadastrarConsulta(boolean cadastrarConsulta) {
        this.cadastrarConsulta = cadastrarConsulta;
    }

    public boolean isLerConsulta() {
        return lerConsulta;
    }

    public void setLerConsulta(boolean lerConsulta) {
        this.lerConsulta = lerConsulta;
    }

    public boolean isAtualizarConsulta() {
        return atualizarConsulta;
    }

    public void setAtualizarConsulta(boolean atualizarConsulta) {
        this.atualizarConsulta = atualizarConsulta;
    }

    public boolean isDeletarConsulta() {
        return deletarConsulta;
    }

    public void setDeletarConsulta(boolean deletarConsulta) {
        this.deletarConsulta = deletarConsulta;
    }

    public boolean isListarConsulta() {
        return listarConsulta;
    }

    public void setListarConsulta(boolean listarConsulta) {
        this.listarConsulta = listarConsulta;
    }

    public boolean isCadastrarEspecialidade() {
        return cadastrarEspecialidade;
    }

    public void setCadastrarEspecialidade(boolean cadastrarEspecialidade) {
        this.cadastrarEspecialidade = cadastrarEspecialidade;
    }

    public boolean isLerEspecialiade() {
        return lerEspecialiade;
    }

    public void setLerEspecialiade(boolean lerEspecialiade) {
        this.lerEspecialiade = lerEspecialiade;
    }

    public boolean isAtualizarEspecialidade() {
        return atualizarEspecialidade;
    }

    public void setAtualizarEspecialidade(boolean atualizarEspecialidade) {
        this.atualizarEspecialidade = atualizarEspecialidade;
    }

    public boolean isDeletarEspecialidade() {
        return deletarEspecialidade;
    }

    public void setDeletarEspecialidade(boolean deletarEspecialidade) {
        this.deletarEspecialidade = deletarEspecialidade;
    }

    public boolean isListarEspecialidade() {
        return listarEspecialidade;
    }

    public void setListarEspecialidade(boolean listarEspecialidade) {
        this.listarEspecialidade = listarEspecialidade;
    }

    public boolean isCadastrarConvenio() {
        return cadastrarConvenio;
    }

    public void setCadastrarConvenio(boolean cadastrarConvenio) {
        this.cadastrarConvenio = cadastrarConvenio;
    }

    public boolean isAtualizarConvenio() {
        return atualizarConvenio;
    }

    public void setAtualizarConvenio(boolean atualizarConvenio) {
        this.atualizarConvenio = atualizarConvenio;
    }

    public boolean isDeletarConvenio() {
        return deletarConvenio;
    }

    public void setDeletarConvenio(boolean deletarConvenio) {
        this.deletarConvenio = deletarConvenio;
    }

    public boolean isListarConvenio() {
        return listarConvenio;
    }

    public void setListarConvenio(boolean listarConvenio) {
        this.listarConvenio = listarConvenio;
    }

    public boolean isCadastrarProntuario() {
        return cadastrarProntuario;
    }

    public void setCadastrarProntuario(boolean cadastrarProntuario) {
        this.cadastrarProntuario = cadastrarProntuario;
    }

    public boolean isLerProntuario() {
        return lerProntuario;
    }

    public void setLerProntuario(boolean lerProntuario) {
        this.lerProntuario = lerProntuario;
    }

    public boolean isAtualizarProntuario() {
        return atualizarProntuario;
    }

    public void setAtualizarProntuario(boolean atualizarProntuario) {
        this.atualizarProntuario = atualizarProntuario;
    }

    public boolean isDeletarProntuario() {
        return deletarProntuario;
    }

    public void setDeletarProntuario(boolean deletarProntuario) {
        this.deletarProntuario = deletarProntuario;
    }

    public boolean isListarPronturario() {
        return listarPronturario;
    }

    public void setListarPronturario(boolean listarPronturario) {
        this.listarPronturario = listarPronturario;
    }

    public boolean isLerConvenio() {
        return lerConvenio;
    }

    public void setLerConvenio(boolean lerConvenio) {
        this.lerConvenio = lerConvenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return id.equals(perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
