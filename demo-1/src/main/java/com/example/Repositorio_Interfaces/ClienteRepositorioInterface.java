package com.example.Repositorio_Interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Entities.Cliente;

@Repository
public interface ClienteRepositorioInterface {
	
	List<Cliente> listar();
	Cliente buscar(Long id);
	Cliente salvar(Cliente cliente);
	void remover(Cliente cliente);

}
