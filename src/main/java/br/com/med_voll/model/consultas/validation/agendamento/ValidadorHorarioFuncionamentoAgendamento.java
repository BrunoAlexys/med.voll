package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoAgendamento implements IValidadorAgendamento {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoExeption("Não é possivel marcar uma consulta fora do horário de funcionamento da clinica!");
        }


    }
}
