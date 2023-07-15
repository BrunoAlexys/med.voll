package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.ConsultaRepository;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteConsultaNoMesmoDiaAgendamento implements IValidadorAgendamento {
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuieOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (pacientePossuieOutraConsultaNoDia) {
            throw new ValidacaoExeption("Paciente já possui uma consulta nesse mesmo dia!");
        }
    }
}
