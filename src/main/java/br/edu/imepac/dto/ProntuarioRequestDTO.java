package br.edu.imepac.dto;

public record ProntuarioRequestDTO(
        String receituario,
        String exame,
        String observacao
) {
}
