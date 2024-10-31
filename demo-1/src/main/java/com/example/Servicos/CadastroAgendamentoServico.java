package com.example.Servicos;

import com.example.Entities.Agendamento;
import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.AgendamentoRepositorioInterface;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroAgendamentoServico {
	
	@Autowired
	private AgendamentoRepositorioInterface agendamentoRepositorioInterface;
	
	public Agendamento salvar(Agendamento agendamento) {
		
		if(agendamento.getDataHora() == null) {
			throw new CampoNomeNaoPodeSerNuloException("Campo 'data e hora' não pode ser nulo!!");
		}

		agendamento = agendamentoRepositorioInterface.salvar(agendamento);
		return agendamento;
		
	}

	public List<Agendamento> listar(){
		return agendamentoRepositorioInterface.listar();
	}


}
