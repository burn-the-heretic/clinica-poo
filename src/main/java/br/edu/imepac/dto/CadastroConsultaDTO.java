package br.edu.imepac.dto;

import br.edu.imepac.entities.Convenio;
import br.edu.imepac.entities.Paciente;
import br.edu.imepac.entities.Prontuario;

import java.time.LocalDateTime;

public record CadastroConsultaDTO(
        LocalDateTime dataHorario,
        String sintomas,
        boolean eRetorno,
        boolean estaAtiva,
        Paciente paciente,
        Prontuario prontuario,
        Convenio convenio
) {
}
