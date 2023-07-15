package br.com.med_voll.model.consultas;

import br.com.med_voll.model.medicos.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull Long idPaciente,

        @NotNull @Future LocalDateTime data,
        Especialidade especialidade) {
}
