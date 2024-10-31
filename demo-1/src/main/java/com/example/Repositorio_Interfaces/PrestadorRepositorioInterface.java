package com.example.Repositorio_Interfaces;

import com.example.Entities.Cliente;
import com.example.Entities.Prestador;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestadorRepositorioInterface {
	
	List<Prestador> listar();
	Prestador buscar(Long id);
	Prestador salvar(Prestador prestador);
	void remover(Prestador prestador);

}
