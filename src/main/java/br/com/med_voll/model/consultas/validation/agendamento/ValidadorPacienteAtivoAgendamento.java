package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import br.com.med_voll.model.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivoAgendamento implements IValidadorAgendamento {
    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidacaoExeption("Consulta não pode ser realizada pois o paciente não está ativo!");
        }
    }
}
