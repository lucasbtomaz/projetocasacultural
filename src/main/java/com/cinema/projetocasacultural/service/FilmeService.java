
package com.cinema.projetocasacultural.service;

import com.cinema.projetocasacultural.data.FilmeEntity;
import com.cinema.projetocasacultural.data.FilmeRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<FilmeEntity> listarTodos() {
        return filmeRepository.findAll();
    }

    public FilmeEntity buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id: " + id));
    }

    public FilmeEntity criar(FilmeEntity filme) {
        return filmeRepository.save(filme);
    }

    public FilmeEntity atualizar(Long id, FilmeEntity atualizado) {
        FilmeEntity existente = filmeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Filme não encontrado com ID: " + id));

        existente.setTitulo(atualizado.getTitulo());
        existente.setSinopse(atualizado.getSinopse());
        existente.setGenero(atualizado.getGenero());
        existente.setAnoLancamento(atualizado.getAnoLancamento());

        return filmeRepository.save(existente);
    }


    public void deletar(Long id) {
        FilmeEntity existente = buscarPorId(id);
        filmeRepository.delete(existente);
    }
}
