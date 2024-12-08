package br.edu.imepac.dto;

import java.time.LocalDateTime;

public record PacienteResponseDTO(
        Integer id,
        String nome,
        Integer idade,
        char sexo,
        String cpf,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String contato,
        String email,
        LocalDateTime dataNascimento
) {
}
