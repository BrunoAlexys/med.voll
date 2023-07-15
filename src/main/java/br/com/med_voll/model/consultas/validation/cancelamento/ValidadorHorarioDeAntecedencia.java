package br.com.med_voll.model.consultas.validation.cancelamento;

import br.com.med_voll.model.ValidacaoExeption;
import br.com.med_voll.model.consultas.ConsultaRepository;
import br.com.med_voll.model.consultas.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioDeAntecedencia implements IValidadorCancelamento {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validador(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoExeption("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}

