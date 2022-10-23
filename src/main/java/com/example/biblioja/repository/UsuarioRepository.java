package com.example.biblioja.repository;

import com.example.biblioja.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
}

