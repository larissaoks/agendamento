package br.com.infnet.agendamento;

import br.com.infnet.agendamento.controller.AgendamentoController;
import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.service.AgendamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgendamentoController.class)
public class AgendamentoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AgendamentoService agendamentoService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testMarcarServico() throws Exception {
        mockMvc.perform(post("/marcarServico")
                .content("application/json")
                .param("nomeCliente", "Larissa")
                .param("cpf", "16558100738")
                .param("dataAgendamento", "22/12/2022")
                .param("servico","corte"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testBuscarAgendaPorCliente() throws Exception{
        Agendamento ag1 = Agendamento.builder().id(1L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("corte").build();
        Agendamento ag2 = Agendamento.builder().id(2L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("Hidratação").build();
        Agendamento ag3 = Agendamento.builder().id(3L).nomeCliente("Maria").cpf("63587566941").dataAgendamento("05/01/2023").servico("Luzes").build();
        Agendamento ag4 = Agendamento.builder().id(4L).nomeCliente("Ana").cpf("65875411254").dataAgendamento("23/12/2022").servico("manicure").build();

        List<Agendamento> agendamento = Arrays.asList(ag1, ag2, ag3, ag4);
        Mockito.when(agendamentoService.buscarAgendaPorCliente("16558100738")).thenReturn(agendamento);

        mockMvc.perform(get("/buscarAgendaPorCliente")
                .contentType("application/json")
                .param("cpf", "16558100738"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testDesmarcarServico() throws  Exception{
        Agendamento ag1 = Agendamento.builder().id(1L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("corte").build();
        Agendamento ag2 = Agendamento.builder().id(2L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("Hidratação").build();
        List<Agendamento> agendamento = Arrays.asList(ag1, ag2);
        Mockito.when(agendamentoService.getById(1L)).thenReturn(Optional.ofNullable(ag1));

        mockMvc.perform(delete("/desmarcarServico")
                .contentType("application/json")
                .param("id", String.valueOf(1L)))
                .andDo(print())
                .andExpect(status().is(200));
    }
}
