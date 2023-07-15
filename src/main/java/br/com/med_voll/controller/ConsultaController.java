package br.com.med_voll.controller;

import br.com.med_voll.model.consultas.AgendaDeConsultas;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import br.com.med_voll.model.consultas.DadosCancelamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultas consultas;
    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = consultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        consultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
