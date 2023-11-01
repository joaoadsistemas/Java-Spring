package com.remedios.joaosilveira.ProjetoRemedios.remedio;

import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Remedio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Via via;
    private String lote;
    private Integer quantidade;
    private LocalDate validade;
    @Enumerated(EnumType.STRING)
    private Laboratorio laboratorio;

    private Boolean ativo;

    public Remedio(String nome, Via via, String lote, Integer quantidade, LocalDate validade, Laboratorio laboratorio) {
        this.nome = nome;
        this.via = via;
        this.lote = lote;
        this.quantidade = quantidade;
        this.validade = validade;
        this.laboratorio = laboratorio;
    }

    public Remedio() {

    }

    public Remedio(DadosCadastroRemedio dados) {
        this.nome = dados.nome();
        this.via = dados.via();
        this.lote = dados.lote();
        this.quantidade = dados.quantidade();
        this.validade = dados.validade();
        this.laboratorio = dados.laboratorio();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Via getVia() {
        return via;
    }

    public void setVia(Via via) {
        this.via = via;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratio) {
        this.laboratorio = laboratio;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public ResponseEntity<Object> setAtivo(Boolean ativo) {
        this.ativo = ativo;
        return ResponseEntity.noContent().build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remedio remedio = (Remedio) o;
        return Objects.equals(id, remedio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
