package br.com.infnet.agendamento.respository;

import br.com.infnet.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findAllByCpf(String cpf);


}
