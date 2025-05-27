package com.cinema.projetocasacultural.dto;
import com.cinema.projetocasacultural.data.AnaliseEntity;
import com.cinema.projetocasacultural.data.FilmeEntity;
import java.util.List;

public class FilmeDetalhadoDTO {

    private FilmeEntity filme; 
    private List<AnaliseEntity> analises; 
    private double media;

    public FilmeDetalhadoDTO(FilmeEntity filme, List<AnaliseEntity> analises, double media) {
        this.filme = filme;
        this.analises = analises;
        this.media = media;
    }

    public FilmeEntity getFilme() { return filme; }
    public List<AnaliseEntity> getAnalises() { return analises; }
    public double getMedia() { return media; }

    public void setFilme(FilmeEntity filme) { this.filme = filme; }
    public void setAnalises(List<AnaliseEntity> analises) { this.analises = analises; }
    public void setMedia(double media) { this.media = media; }
}