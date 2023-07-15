package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioAntecedenciaAgendamento implements IValidadorAgendamento {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var horaAtual = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(horaAtual,dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoExeption("Consulta deve ser marcada com atecedencia de 30 minutos!");
        }
    }
}
