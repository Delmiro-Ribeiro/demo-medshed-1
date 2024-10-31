package com.example.Controllers;

import com.example.Entities.Cliente;
import com.example.Entities.Servico;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Repositorio_Interfaces.ServicoRepositorioInterface;
import com.example.Servicos.CadastroClienteServico;
import com.example.Servicos.CadastroServicoServico;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {
	
	@Autowired
	private ServicoRepositorioInterface servicoRepositorio;
	
	@Autowired
	private CadastroServicoServico servicoDoServico;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Servico> listarServico() {
		return servicoRepositorio.listar();
	}
	
	@GetMapping("/{servicoId}")
	public ResponseEntity<Servico> buscarServico(@PathVariable Long servicoId) {
		
		Servico servico = servicoRepositorio.buscar(servicoId);
		
		if (servico != null) {
			return ResponseEntity.ok(servico);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus //(HttpStatus.CREATED)
	public ResponseEntity<?> adicionarServico(@RequestBody Servico servico) {
		try {
			servico = servicoDoServico.salvar(servico);
			return ResponseEntity.status(201).body(servico);
		} catch (CampoNomeNaoPodeSerNuloException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		
	}
	
	@PutMapping("/{servicoId}")
	public ResponseEntity<Servico> atualizar(@PathVariable Long servicoId, @RequestBody Servico servico) {
		Servico servicoAtual = servicoRepositorio.buscar(servicoId);
		
		if (servicoAtual != null) {
			BeanUtils.copyProperties(servico, servicoAtual, "id");
			servicoAtual = servicoRepositorio.salvar(servicoAtual);
			return ResponseEntity.ok(servicoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{servicoId}")
	public ResponseEntity<Servico> remover(@PathVariable Long servicoId) {
		try {
			Servico servico = servicoRepositorio.buscar(servicoId);
			
			if (servico != null) {
				servicoRepositorio.remover(servico);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}


