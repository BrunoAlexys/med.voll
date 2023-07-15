package br.com.med_voll.model.consultas;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.validation.agendamento.IValidadorAgendamento;
import br.com.med_voll.model.consultas.validation.cancelamento.IValidadorCancelamento;
import br.com.med_voll.model.medicos.Medico;
import br.com.med_voll.model.medicos.MedicoRepository;
import br.com.med_voll.model.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<IValidadorAgendamento> validadores;
    @Autowired
    private List<IValidadorCancelamento> validadorCancelamentos;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoExeption("ID do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoExeption("ID do medico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoExeption("Não existe medicos disponiveis para consulta nesse momento,tente mais tarde!");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoExeption("Especialidade é obrigatoria quando o medico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoExeption("ID informado não existe!");
        }

        validadorCancelamentos.forEach(v -> v.validador(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
