package br.com.med_voll.model.medicos;

import br.com.med_voll.model.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco dadosEndereco) {
}
