package com.example.biblioja.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
public class Bibliotecario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    
    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String nome;
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    @Size(min = 8, max = 8)
    private String senha;
    
    
}

