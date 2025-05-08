package com.cinema.projetocasacultural.data;

import com.cinema.projetocasacultural.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AnaliseRepository extends JpaRepository<Filme, Long> {
    
}
