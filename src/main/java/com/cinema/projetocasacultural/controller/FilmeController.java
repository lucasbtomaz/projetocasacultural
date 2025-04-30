package com.cinema.projetocasacultural.controller;

import com.cinema.projetocasacultural.model.Analise;
import com.cinema.projetocasacultural.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private List<Filme> filmes = new ArrayList<>();
    private List<Analise> analises = new ArrayList<>();
    private Long proximoId = 1L;
    private Long proximoIdAnalise = 1L;

    @GetMapping("/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("filme", new Filme());
        return "filmes/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarFilme(@ModelAttribute Filme filme) {
        filme.setId(proximoId++);
        filmes.add(filme);
        return "redirect:/filmes/lista";
    }

    @GetMapping("/lista")
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "filmes/lista";
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public Filme buscarPorId(Long id) {
        return filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/avaliar/{id}")
    public String avaliarFilme(@PathVariable("id") Long id, Model model) {
        Filme filme = buscarPorId(id);
        if (filme != null) {
            model.addAttribute("filme", filme);
            model.addAttribute("analise", new Analise());
            return "filmes/avaliar";
        }
        return "redirect:/filmes/lista";
    }

    @PostMapping("/salvar-analise")
    public String salvarAnalise(@ModelAttribute Analise analise) {
        analise.setId(proximoIdAnalise++);
        analises.add(analise);

        Filme filme = buscarPorId(analise.getFilme().getId());
        if (filme != null) {
            filme.getAnalises().add(analise);
        }

        return "redirect:/filmes/lista";
    }

    @GetMapping("/detalhes/{id}")
    public String mostrarDetalhes(@PathVariable Long id, Model model) {
        Filme filme = buscarPorId(id);

        if (filme == null) {
            return "redirect:/filmes/lista";
        }

        double media = calcularMedia(filme);

        model.addAttribute("filme", filme);
        model.addAttribute("avaliacoes", filme.getAnalises());
        model.addAttribute("media", media);
        model.addAttribute("analiseForm", new Analise());

        return "filmes/detalhes";
    }

    private double calcularMedia(Filme filme) {
        var avaliacoes = filme.getAnalises();
        if (avaliacoes.isEmpty()) {
            return 0;
        }
        return avaliacoes.stream().mapToInt(Analise::getNota).average().orElse(0);
    }
}
