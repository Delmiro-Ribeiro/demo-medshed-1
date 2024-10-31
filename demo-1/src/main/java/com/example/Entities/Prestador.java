package com.example.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Prestador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = true)
    private String email;
	
	@Column(nullable = true)
    private String senha;
	
	@Column(nullable = false)
    private String telefone;

	@Column(nullable = true)
	private String especialidade;

	public Prestador(){}

	@OneToMany(mappedBy = "prestador", cascade = CascadeType.ALL)
	private List<Agendamento> agendamento = new ArrayList<>();
//-------------------------------------------------------------------------
	public List<Agendamento> getAgendamento() {
		return agendamento;
	}

	public void setAgendamentos(List<Agendamento> agendamento) {
		this.agendamento = agendamento;
	}


	@ManyToMany
	@JoinTable(
			name = "prestador_servico",
			joinColumns = @JoinColumn(name = "prestador_id"),
			inverseJoinColumns = @JoinColumn(name = "servico_id")
	)
	private List<Servico> servico = new ArrayList<>();

	public List<Servico> getServico() {
		return servico;
	}

	public void setServico(List<Servico> servico) {
		this.servico = servico;
	}

//----------------------------------------------------------------------


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestador other = (Prestador) obj;
		return id == other.id;
	}

}
