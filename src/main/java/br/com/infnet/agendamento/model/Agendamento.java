package br.com.infnet.agendamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private String cpf;
    private String dataAgendamento;
    private String servico;

    public Agendamento(String nomeCliente, String cpf, String dataAgendamento, String servico){
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.dataAgendamento = dataAgendamento;
        this.servico = servico;
    }
}
