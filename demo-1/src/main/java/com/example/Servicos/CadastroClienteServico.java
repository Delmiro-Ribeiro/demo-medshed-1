package com.example.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;

@Service
public class CadastroClienteServico {
	
	@Autowired
	private ClienteRepositorioInterface clienteRepositorioInterface;
	
	public Cliente salvar(Cliente cliente) {
		
		if(cliente.getNome() == null) {
			throw new CampoNomeNaoPodeSerNuloException("Campo 'nome' não pode ser nulo!!");
		}
		
		cliente = clienteRepositorioInterface.salvar(cliente);
		return cliente;
		
	}
}
