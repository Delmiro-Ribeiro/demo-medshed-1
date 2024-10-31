package com.example.Controllers;

import com.example.Entities.Cliente;
import com.example.Entities.Prestador;
import com.example.Repositorio_Interfaces.ClienteRepositorioInterface;
import com.example.Repositorio_Interfaces.PrestadorRepositorioInterface;
import com.example.Servicos.CadastroClienteServico;
import com.example.Servicos.CadastroPrestadorServico;
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
@RequestMapping(value = "/prestador")
public class PrestadorController {
	
	@Autowired
	private PrestadorRepositorioInterface prestadorRepositorio;
	
	@Autowired
	private CadastroPrestadorServico prestadorServico;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Prestador> listarPrestador() {
		return prestadorRepositorio.listar();
	}
	
	@GetMapping("/{prestadorId}")
	public ResponseEntity<Prestador> buscarPrestador(@PathVariable Long prestadorId) {
		
		Prestador prestador = prestadorRepositorio.buscar(prestadorId);
		
		if (prestador != null) {
			return ResponseEntity.ok(prestador);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus //(HttpStatus.CREATED)
	public ResponseEntity<?> adicionarPrestador(@RequestBody Prestador prestador) {
		try {
			prestador = prestadorServico.salvar(prestador);
			return ResponseEntity.status(201).body(prestador);
		} catch (CampoNomeNaoPodeSerNuloException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} 
		
	}
	
	@PutMapping("/{prestadorid}")
	public ResponseEntity<Prestador> atualizarPrestador(@PathVariable Long prestadorId, @RequestBody Prestador prestador) {
		Prestador prestadorAtual = prestadorRepositorio.buscar(prestadorId);
		
		if (prestadorAtual != null) {
			BeanUtils.copyProperties(prestador, prestadorAtual, "id");
			prestadorAtual = prestadorRepositorio.salvar(prestadorAtual);
			return ResponseEntity.ok(prestadorAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{prestadorId}")
	public ResponseEntity<Cliente> removerPrestador(@PathVariable Long prestadorId) {
		try {
			Prestador prestador = prestadorRepositorio.buscar(prestadorId);
			
			if (prestador != null) {
				prestadorRepositorio.remover(prestador);
				
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}


