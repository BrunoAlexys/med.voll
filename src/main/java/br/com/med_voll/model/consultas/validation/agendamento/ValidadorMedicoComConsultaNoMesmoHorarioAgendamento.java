package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.ConsultaRepository;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsultaNoMesmoHorarioAgendamento implements IValidadorAgendamento {
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),dados.data());
        if (medicoPossuiConsultaNoMesmoHorario) {
            throw new ValidacaoExeption("Medico já possui consulta nesse mesmo horario!");
        }
    }
}
