package br.edu.imepac.dto;

import br.edu.imepac.entities.Consulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
         LocalDateTime dataHorario,
         String sintomas ,
         boolean eRetorno,
         boolean estaAtiva,
         int pacienteId,
         int prontuarioId ,
         int convenioId
) {
}
