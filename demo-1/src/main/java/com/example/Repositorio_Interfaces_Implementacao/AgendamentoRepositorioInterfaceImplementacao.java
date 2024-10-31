package com.example.Repositorio_Interfaces_Implementacao;

import com.example.Entities.Agendamento;
import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.AgendamentoRepositorioInterface;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgendamentoRepositorioInterfaceImplementacao implements AgendamentoRepositorioInterface {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Agendamento> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Agendamento", Agendamento.class).getResultList();
	}

	@Override
	public Agendamento buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Agendamento.class, id);
	}
	
	@Transactional
	@Override
	public Agendamento salvar(Agendamento agendamento) {
		// TODO Auto-generated method stub
		return manager.merge(agendamento);
	}
	
	@Transactional
	@Override
	public void remover(Agendamento agendamento) {
		// TODO Auto-generated method stub
		agendamento = buscar(agendamento.getId());
		manager.remove(agendamento);
	}

}
