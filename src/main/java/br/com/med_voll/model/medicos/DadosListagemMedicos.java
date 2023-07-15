package br.com.med_voll.model.medicos;

public record DadosListagemMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
