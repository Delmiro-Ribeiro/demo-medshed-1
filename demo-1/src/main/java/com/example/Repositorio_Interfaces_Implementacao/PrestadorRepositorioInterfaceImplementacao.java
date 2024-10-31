package com.example.Repositorio_Interfaces_Implementacao;

import com.example.Entities.Cliente;
import com.example.Entities.Prestador;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Repositorio_Interfaces.PrestadorRepositorioInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrestadorRepositorioInterfaceImplementacao implements PrestadorRepositorioInterface {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Prestador> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Cliente", Prestador.class).getResultList();
	}

	@Override
	public Prestador buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Prestador.class, id);
	}
	
	@Transactional
	@Override
	public Prestador salvar(Prestador prestador) {
		// TODO Auto-generated method stub
		return manager.merge(prestador);
	}
	
	@Transactional
	@Override
	public void remover(Prestador prestador) {
		// TODO Auto-generated method stub
		prestador = buscar(prestador.getId());
		manager.remove(prestador);
	}

}
