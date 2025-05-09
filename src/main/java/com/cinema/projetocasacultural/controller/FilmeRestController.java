package com.cinema.projetocasacultural.controller;

import com.cinema.projetocasacultural.data.FilmeEntity;
import com.cinema.projetocasacultural.service.FilmeService;
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

    private final FilmeService filmeService;

    public FilmeRestController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<FilmeEntity> listarTodos() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FilmeEntity> criar(@RequestBody FilmeEntity filme) {
        return ResponseEntity.ok(filmeService.criar(filme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeEntity> atualizar(@PathVariable Long id, @RequestBody FilmeEntity atualizado) {
        return ResponseEntity.ok(filmeService.atualizar(id, atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
