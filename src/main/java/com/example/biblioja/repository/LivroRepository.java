package com.example.biblioja.repository;

import com.example.biblioja.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface LivroRepository extends JpaRepository<Livro, UUID>{
    
}

