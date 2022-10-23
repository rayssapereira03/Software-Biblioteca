package com.example.biblioja.repository;

import com.example.biblioja.model.Bibliotecario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, UUID>{
    
}

