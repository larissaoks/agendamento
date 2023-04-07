package br.com.infnet.agendamento.service;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.respository.AgendamentoRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    public Optional<Agendamento> getById(Long id){
        return agendamentoRepository.findById(id);
    }

    @Timed(value = "salvar", description = "métrica para avaliar o tempo que leva para salvar no banco o novo agendamento.")
    public void salvar(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id){
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> buscarAgendaPorCliente(String cpf){
        return agendamentoRepository.findAllByCpf(cpf);
    }

    @Counted(value = "buscar_agenda", description = "métrica para avaliar quantas vezes a função foi chamada.")
    public List<Agendamento> buscarAgenda(){
        return agendamentoRepository.findAll();
    }
}
