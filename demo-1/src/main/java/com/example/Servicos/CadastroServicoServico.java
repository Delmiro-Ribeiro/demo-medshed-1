package com.example.Servicos;

import com.example.Entities.Agendamento;
import com.example.Entities.Servico;
import com.example.Repositorio_Interfaces.AgendamentoRepositorioInterface;
import com.example.Repositorio_Interfaces.ServicoRepositorioInterface;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroServicoServico {
	
	@Autowired
	private ServicoRepositorioInterface servicoRepositorioInterface;
	
	public Servico salvar(Servico servico) {
		
		if(servico.getDescricao() == null) {
			throw new CampoNomeNaoPodeSerNuloException("Campo 'descricao' não pode ser nulo!!");
		}

		servico = servicoRepositorioInterface.salvar(servico);
		return servico;
		
	}

	public List<Servico> listar(){
		return servicoRepositorioInterface.listar();
	}


}
