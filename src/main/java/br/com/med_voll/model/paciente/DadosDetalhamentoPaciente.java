package br.com.med_voll.model.paciente;

import br.com.med_voll.model.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String telefone, String email, String cpf,
                                        Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
