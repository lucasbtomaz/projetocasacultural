package com.cinema.projetocasacultural.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name="filme")
public class FilmeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    
    @NotNull(message="Titulo obrigatorio") 
    private String titulo;
    
    @Size(min=2, message="Informe ao menos 2 caracteres para o campo sinopse") 
    private String sinopse;
    
    @NotNull(message="Genero obrigatorio")
    private String genero;
     
    private int anoLancamento;
    
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnaliseEntity> analises;
}
