package com.cinema.projetocasacultural.controller;

import com.cinema.projetocasacultural.data.FilmeRepository;
import com.cinema.projetocasacultural.model.Filme;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    private final FilmeRepository filmeRepository;

    public FilmeRestController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Filme criar(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @RequestBody Filme atualizado) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(atualizado.getTitulo());
                    filme.setSinopse(atualizado.getSinopse());
                    return ResponseEntity.ok(filmeRepository.save(filme));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeRepository.delete(filme);
                    return ResponseEntity.<Void>noContent().build(); 
                }).orElse(ResponseEntity.notFound().build());
    }

}
