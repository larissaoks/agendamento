package br.com.infnet.agendamento.controller;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping("buscarAgendaPorCliente")
    public ResponseEntity<Map<String,Object>> buscarAgendaPorCliente(@RequestParam String cpf){
        List<Agendamento> agendaDoCliente = agendamentoService.buscarAgendaPorCliente(cpf);
        if (agendaDoCliente.isEmpty()){
            return ResponseEntity.ok(Map.of("Aviso: ", "Não há nenhum serviço agendado para o CPF: " + cpf));
        }
        return ResponseEntity.ok(Map.of("Agenda de " + cpf, agendaDoCliente));
    }

    @GetMapping("buscarAgenda")
    public ResponseEntity<Map<String,Object>> buscarAgenda(){
        List<Agendamento> agendaCompleta = agendamentoService.buscarAgenda();
        if(agendaCompleta.isEmpty()){
            return ResponseEntity.ok(Map.of("Aviso: ", "Não há serviço agendado"));
        }
        return ResponseEntity.ok(Map.of("Agenda: ", agendaCompleta));
    }

    @PostMapping("marcarServico")
    public ResponseEntity<String> marcarServico(@RequestParam String nomeCliente, @RequestParam String cpf, @RequestParam String dataAgendamento, @RequestParam String servico){
        agendamentoService.salvar(new Agendamento(nomeCliente, cpf,dataAgendamento, servico));

        return ResponseEntity.ok("Servico marcado para " + nomeCliente + " dia: " + dataAgendamento);
    }


    @DeleteMapping("desmarcarServico")
    public ResponseEntity<String> desmarcarServico(@RequestParam Long id){
        Optional<Agendamento> agenda = agendamentoService.getById(id);
        if (agenda.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum serviço encontrado com o id " + id);
        }
        else {
            agendamentoService.deletar(id);
        }
        return ResponseEntity.status(200).body("Servico desmarcado!");
    }
}
