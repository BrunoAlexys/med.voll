package br.com.med_voll.model.medicos;

import br.com.med_voll.model.consultas.AgendaDeConsultas;
import br.com.med_voll.model.consultas.DadosAgendamentoConsulta;
import br.com.med_voll.model.consultas.DadosDetalhamentoConsulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> consultaJacksonTester;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaTester;
    @MockBean
    private AgendaDeConsultas consultas;

    @Test
    @DisplayName("Deveria devolver codigo 400 quando a informação for invalida")
    @WithMockUser
    public void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 200 quando a informação for invalida")
    @WithMockUser
    public void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.cardiologia;

        var dadosDeetalhamento = new DadosDetalhamentoConsulta(null,2L,4L,data);
        when(consultas.agendar(any())).thenReturn(dadosDeetalhamento);

        var response = mvc.perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(consultaJacksonTester.write(
                                        new DadosAgendamentoConsulta(2L, 4L, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jasonEsperado = dadosDetalhamentoConsultaTester.write(dadosDeetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jasonEsperado);
    }
}
