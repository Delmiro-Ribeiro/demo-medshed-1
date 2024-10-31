package com.example.Repositorio_Interfaces;

import com.example.Entities.Agendamento;
import com.example.Entities.Servico;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepositorioInterface {
	
	List<Servico> listar();
	Servico buscar(Long id);
	Servico salvar(Servico servico);
	void remover(Servico servico);

}
