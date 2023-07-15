package br.com.med_voll.model.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo) {
}
