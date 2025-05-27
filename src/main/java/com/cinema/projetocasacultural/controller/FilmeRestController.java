package com.cinema.projetocasacultural.controller;

import com.cinema.projetocasacultural.data.AnaliseEntity;
import com.cinema.projetocasacultural.data.FilmeEntity;
import com.cinema.projetocasacultural.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cinema.projetocasacultural.dto.FilmeDetalhadoDTO;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
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

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<AnaliseEntity> salvarAnalise(@PathVariable Long id, @RequestBody AnaliseEntity analise) {
        AnaliseEntity nova = filmeService.adicionarAnalise(id, analise);
        return ResponseEntity.ok(nova);
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<FilmeDetalhadoDTO> mostrarDetalhes(@PathVariable Long id) {
        FilmeDetalhadoDTO dto = filmeService.buscarDetalhes(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{filmeId}/analises/{analiseId}")
    public ResponseEntity<AnaliseEntity> buscarAnalisePorId(
            @PathVariable Long filmeId,
            @PathVariable Long analiseId
    ) {
        AnaliseEntity analise = filmeService.buscarAnalisePorId(filmeId, analiseId);
        return ResponseEntity.ok(analise);
    }

    @DeleteMapping("/{filmeId}/analises/{analiseId}")
    public ResponseEntity<Void> excluirAnalise(
            @PathVariable Long filmeId,
            @PathVariable Long analiseId
    ) {
        filmeService.excluirAnalise(filmeId, analiseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{filmeId}/analises/{analiseId}")
    public ResponseEntity<AnaliseEntity> editarAnalise(
            @PathVariable Long filmeId,
            @PathVariable Long analiseId,
            @RequestBody AnaliseEntity novaAnalise) {

        AnaliseEntity analiseAtualizada = filmeService.editarAnalise(filmeId, analiseId, novaAnalise);
        return ResponseEntity.ok(analiseAtualizada);
    }
}
