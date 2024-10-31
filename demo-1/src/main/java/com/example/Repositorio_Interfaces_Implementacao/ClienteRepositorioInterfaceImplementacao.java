package com.example.Repositorio_Interfaces_Implementacao;

import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;

@Component
public class ClienteRepositorioInterfaceImplementacao implements ClienteRepositorioInterface {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Override
	public Cliente buscar(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Cliente.class, id);
	}
	
	@Transactional
	@Override
	public Cliente salvar(Cliente cliente) {
		// TODO Auto-generated method stub
		return manager.merge(cliente);
	}
	
	@Transactional
	@Override
	public void remover(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente = buscar(cliente.getId());
		manager.remove(cliente);
	}

}
