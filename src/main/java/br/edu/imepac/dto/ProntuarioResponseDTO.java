package br.edu.imepac.dto;

public record ProntuarioResponseDTO(
        Integer id,
        String receituario,
        String exame,
        String observacao
) {
}
