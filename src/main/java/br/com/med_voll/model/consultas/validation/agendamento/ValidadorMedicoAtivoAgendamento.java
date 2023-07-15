package br.com.med_voll.model.consultas.validation.agendamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import br.com.med_voll.model.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivoAgendamento implements IValidadorAgendamento {
    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoExeption("Consulta não pode ser agendada com esse medico,pois ele não está ativo em nosso sistema");
        }
    }
}
