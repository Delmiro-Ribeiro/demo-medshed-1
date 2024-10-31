package com.example.Controllers;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Cliente;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Servicos.CadastroClienteServico;
import com.examples.Excecoes.CampoNomeNaoPodeSerNuloException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepositorioInterface clienteRepositorio;
	
	@Autowired
	private CadastroClienteServico clienteServico;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listar() {
		return clienteRepositorio.listar();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		
		Cliente cliente = clienteRepositorio.buscar(clienteId);
		
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus //(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Cliente cliente) {
		try {
			cliente = clienteServico.salvar(cliente);
			return ResponseEntity.status(201).body(cliente);
		} catch (CampoNomeNaoPodeSerNuloException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		Cliente clienteAtual = clienteRepositorio.buscar(clienteId);
		
		if (clienteAtual != null) {
			BeanUtils.copyProperties(cliente, clienteAtual, "id");
			clienteAtual = clienteRepositorio.salvar(clienteAtual);
			return ResponseEntity.ok(clienteAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Cliente> remover(@PathVariable Long clienteId) {
		try {
			Cliente cliente = clienteRepositorio.buscar(clienteId);
			
			if (cliente != null) {
				clienteRepositorio.remover(cliente);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}


