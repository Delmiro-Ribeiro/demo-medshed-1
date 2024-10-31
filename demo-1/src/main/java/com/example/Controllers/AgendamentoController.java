package com.example.Controllers;

import com.example.Entities.Agendamento;
import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.AgendamentoRepositorioInterface;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Servicos.CadastroAgendamentoServico;
import com.example.Servicos.CadastroClienteServico;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepositorioInterface agendamentoRepositorio;
	
	@Autowired
	private CadastroAgendamentoServico agendamentoServico;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Agendamento> listarAgendamento() {
		return agendamentoServico.listar();
	}

	
	@PostMapping
	@ResponseStatus //(HttpStatus.CREATED)
	public ResponseEntity<?> adicionarAgendamento(@RequestBody Agendamento agendamento) {
		try {
			agendamento = agendamentoServico.salvar(agendamento);
			return ResponseEntity.status(201).body(agendamento);
		} catch (CampoNomeNaoPodeSerNuloException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		
	}
	
	@PutMapping("/{agendamentoId}")
	public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable Long agendamentoId, @RequestBody Agendamento agendamento) {
		Agendamento agendamentoAtual = agendamentoRepositorio.buscar(agendamentoId);
		
		if (agendamentoAtual != null) {
			BeanUtils.copyProperties(agendamento, agendamentoAtual, "id");
			agendamentoAtual = agendamentoRepositorio.salvar(agendamentoAtual);
			return ResponseEntity.ok(agendamentoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{agendamentoId}")
	public ResponseEntity<Agendamento> removerAgendamento(@PathVariable Long agendamentoId) {
		try {
			Agendamento agendamento = agendamentoRepositorio.buscar(agendamentoId);
			
			if (agendamento != null) {
				agendamentoRepositorio.remover(agendamento);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}


