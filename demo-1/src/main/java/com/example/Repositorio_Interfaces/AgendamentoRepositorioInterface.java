package com.example.Repositorio_Interfaces;

import com.example.Entities.Agendamento;
import com.example.Entities.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepositorioInterface {
	
	List<Agendamento> listar();
	Agendamento buscar(Long id);
	Agendamento salvar(Agendamento agendamento);
	void remover(Agendamento agendamento);

}
