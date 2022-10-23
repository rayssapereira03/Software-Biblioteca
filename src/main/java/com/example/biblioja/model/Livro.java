
package com.example.biblioja.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    
    @NotNull
    @Size(min = 3, max = 10)
    private String codigo;
    
    @NotNull
    @Size(min = 3, max = 10)
    private String categoria;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String titulo;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String autor;
    
    @NotNull
    @Size(min = 4, max = 4)
    private String anoPublicacao;
    
    @NotNull
    @Size(min = 11, max = 255)
    private String editora;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String status;
}
