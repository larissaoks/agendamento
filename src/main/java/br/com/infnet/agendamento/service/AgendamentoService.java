package br.com.infnet.agendamento.service;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.respository.AgendamentoRepository;
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

    public void salvar(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id){
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> buscarAgendaPorCliente(String cpf){
        return agendamentoRepository.findAllByCpf(cpf);
    }

    public List<Agendamento> buscarAgenda(){
        return agendamentoRepository.findAll();
    }
}
