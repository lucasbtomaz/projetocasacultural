package com.cinema.projetocasacultural.service;

import com.cinema.projetocasacultural.data.AnaliseEntity;
import com.cinema.projetocasacultural.data.AnaliseRepository;
import com.cinema.projetocasacultural.data.FilmeEntity;
import com.cinema.projetocasacultural.data.FilmeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.cinema.projetocasacultural.dto.FilmeDetalhadoDTO;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final AnaliseRepository analiseRepository;

    public FilmeService(FilmeRepository filmeRepository, AnaliseRepository analiseRepository) {
        this.filmeRepository = filmeRepository;
        this.analiseRepository = analiseRepository;
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

    public AnaliseEntity adicionarAnalise(Long filmeId, AnaliseEntity analise) {
        FilmeEntity filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id: " + filmeId));

        analise.setFilme(filme);
        return analiseRepository.save(analise);
    }

    public FilmeDetalhadoDTO buscarDetalhes(Long id) {
        FilmeEntity filmeEntity = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id: " + id));

        System.out.println("Análises carregadas: " + filmeEntity.getAnalises().size());

        List<AnaliseEntity> analises = filmeEntity.getAnalises();

        double media = analises.stream()
                .mapToInt(AnaliseEntity::getNota)
                .average()
                .orElse(0.0);

        return new FilmeDetalhadoDTO(filmeEntity, analises, media);
    }

    public AnaliseEntity buscarAnalisePorId(Long filmeId, Long analiseId) {
        FilmeEntity filme = buscarPorId(filmeId);

        return filme.getAnalises()
                .stream()
                .filter(a -> a.getId().equals(analiseId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Análise não encontrada com id: " + analiseId));
    }

    public void excluirAnalise(Long filmeId, Long analiseId) {
        FilmeEntity filme = buscarPorId(filmeId);

        AnaliseEntity analiseParaExcluir = filme.getAnalises()
                .stream()
                .filter(a -> a.getId().equals(analiseId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Análise não encontrada para exclusão."));

        filme.getAnalises().remove(analiseParaExcluir);

        filmeRepository.save(filme);
    }

    public AnaliseEntity editarAnalise(Long filmeId, Long analiseId, AnaliseEntity novaAnalise) {
        AnaliseEntity analise = buscarAnalisePorId(filmeId, analiseId);

        analise.setAnalise(novaAnalise.getAnalise());
        analise.setNota(novaAnalise.getNota());

        return analiseRepository.save(analise);
    }

}
