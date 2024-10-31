package com.example.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int duracao; //coloquei int para melhor manipulação, mas o certo é a class Duration

    @Column(nullable = false)
    private double preco;

    public Servico(){}

    @ManyToMany(mappedBy = "servico")
    private List<Prestador> prestador = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Prestador> getPrestador() {
        return prestador;
    }

    public void setPrestador(List<Prestador> prestador) {
        this.prestador = prestador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servico servico)) return false;
        return getId() == servico.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
