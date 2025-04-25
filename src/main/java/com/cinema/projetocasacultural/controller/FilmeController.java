/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.projetocasacultural.controller;

import com.cinema.projetocasacultural.model.Analise;
import com.cinema.projetocasacultural.model.Filme;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 *
 * @author oluca
 */
@Controller
@RequestMapping("/filmes")
public class FilmeController {
   private List<Filme> filmes = new ArrayList<>();
    private Long contadorId = 1L;

    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmes);
        return "lista-filmes";
    }

    @GetMapping("/novo")
    public String novoFilmeForm(Model model) {
        model.addAttribute("filme", new Filme(null, "", "", "", 0));
        return "form-filme";
    }

    @PostMapping("/salvar")
    public String salvarFilme(@ModelAttribute Filme filme) {
        filme = new Filme(contadorId++, filme.getTitulo(), filme.getSinopse(), filme.getGenero(), filme.getAnoLancamento());
        filmes.add(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (filme == null) return "redirect:/filmes";

        model.addAttribute("filme", filme);
        model.addAttribute("analise", new Analise(null, null, "", 0));
        return "detalhes-filme";
    }

    @PostMapping("/{id}/analisar")
    public String adicionarAnalise(@PathVariable Long id, @ModelAttribute Analise analise) {
        Filme filme = filmes.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
        if (filme != null) {
            filme.adicionarAnalise(analise);
        }
        return "redirect:/filmes/" + id;
    }
}
