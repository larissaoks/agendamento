package br.com.infnet.agendamento;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.respository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AgendamentoRepositoryTest {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @BeforeEach
    public void agendaTest(){
        agendamentoRepository.save(Agendamento.builder().id(1L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("corte").build());
        agendamentoRepository.save(Agendamento.builder().id(2L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("Hidratação").build());
        agendamentoRepository.save(Agendamento.builder().id(3L).nomeCliente("Maria").cpf("63587566941").dataAgendamento("05/01/2023").servico("Luzes").build());
        agendamentoRepository.save(Agendamento.builder().id(4L).nomeCliente("Ana").cpf("65875411254").dataAgendamento("23/12/2022").servico("manicure").build());
        agendamentoRepository.save(Agendamento.builder().id(5L).nomeCliente("José").cpf("33569855641").dataAgendamento("26/12/2022").servico("barba").build());
    }

    @Test
    public void testFindAllByCpf(){
        List<Agendamento> agenda = agendamentoRepository.findAllByCpf("16558100738");
        assertEquals(agenda.get(0),Agendamento.builder().id(1L).nomeCliente("Larissa").cpf("16558100738").dataAgendamento("23/12/2022").servico("corte").build());
    }


    @Test
    public void testAgenda(){
        List<Agendamento> agenda = agendamentoRepository.findAll();
        assertEquals(5,agenda.size());

    }


}
