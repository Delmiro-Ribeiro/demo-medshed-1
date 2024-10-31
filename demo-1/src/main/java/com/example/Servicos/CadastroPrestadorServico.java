package com.example.Servicos;

import com.example.Entities.Cliente;
import com.example.Entities.Prestador;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Repositorio_Interfaces.PrestadorRepositorioInterface;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPrestadorServico {
	
	@Autowired
	private PrestadorRepositorioInterface prestadorRepositorioInterface;
	
	public Prestador salvar(Prestador prestador) {
		
		if(prestador.getNome() == null) {
			throw new CampoNomeNaoPodeSerNuloException("Campo 'Nome' não pode ser nulo!!");
		}

		prestador = prestadorRepositorioInterface.salvar(prestador);
		return prestador;
		
	}
}
