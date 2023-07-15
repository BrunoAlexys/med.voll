package br.com.med_voll.model.paciente;

import br.com.med_voll.model.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco dadosEndereco) {
}
