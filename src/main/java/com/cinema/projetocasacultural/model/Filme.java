package com.cinema.projetocasacultural.model;

import java.util.ArrayList;
import java.util.List;

public class Filme {

    private Long id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int anoLancamento;

    private List<Analise> analises = new ArrayList<>();

    public Filme() {}

    public Filme(Long id, String titulo, String sinopse, String genero, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getSinopse() { return sinopse; }
    public String getGenero() { return genero; }
    public int getAnoLancamento() { return anoLancamento; }
    public List<Analise> getAnalises() { return analises; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    public void adicionarAnalise(Analise analise) {
        this.analises.add(analise);
    }
}
