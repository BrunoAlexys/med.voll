package br.com.med_voll.model.consultas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);
    Boolean existsByPacienteIdAndDataBetween(Long idMedico, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
