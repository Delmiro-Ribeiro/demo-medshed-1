package com.example.Repositorio_Interfaces_Implementacao;

import com.example.Entities.Agendamento;
import com.example.Entities.Servico;
import com.example.Repositorio_Interfaces.AgendamentoRepositorioInterface;
import com.example.Repositorio_Interfaces.ServicoRepositorioInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicoRepositorioInterfaceImplementacao implements ServicoRepositorioInterface {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Servico> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Agendamento", Servico.class).getResultList();
	}

	@Override
	public Servico buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Servico.class, id);
	}
	
	@Transactional
	@Override
	public Servico salvar(Servico servico) {
		// TODO Auto-generated method stub
		return manager.merge(servico);
	}
	
	@Transactional
	@Override
	public void remover(Servico servico) {
		// TODO Auto-generated method stub
		servico = buscar(servico.getId());
		manager.remove(servico);
	}

}
