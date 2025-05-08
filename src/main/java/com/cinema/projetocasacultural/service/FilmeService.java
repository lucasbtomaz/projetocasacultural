package com.cinema.projetocasacultural.service;

import com.cinema.projetocasacultural.data.FilmeEntity;
import com.cinema.projetocasacultural.data.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List; 


@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmerepository;

    public FilmeEntity criarFilme(FilmeEntity film) {
    film.setId(null);
    return filmerepository.save(film); 
}


    public FilmeEntity atualizarFilme() {
        return film;
    }

    public FilmeEntity getFilmeId(Integer funcId) {
        return filmerepository.findById(filmId).orElse(null);
    }

    public List<FilmeEntity> listarTodosOsFilmes() {
        return filmerepository.findAll();
    }

    public void deletarFuncionario(Integer funcId) {
        FilmeEntity func = getFilmeId(funcId);
        filmerepository.deleteById(func.getId());
    }
}