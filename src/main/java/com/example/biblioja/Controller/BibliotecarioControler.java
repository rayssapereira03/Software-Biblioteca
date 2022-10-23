
package com.example.biblioja.Controller;

import com.example.biblioja.model.Bibliotecario;
import com.example.biblioja.repository.BibliotecarioRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BibliotecarioControler {
     @Autowired
    BibliotecarioRepository bibliotecarioReporitory;

    @GetMapping("/bibliotecarios")
    public ResponseEntity<List<Bibliotecario>> getAliProducts() {
        return new ResponseEntity<>(bibliotecarioReporitory.findAll(), HttpStatus.OK);

    }

    @GetMapping("/bibliotecarios/{id}")
    public ResponseEntity<Bibliotecario>
            getOneProduts(@PathVariable(value = "id") UUID id) {
        Optional<Bibliotecario> bibliotecario0 = (bibliotecarioReporitory.findById(id));
        if (bibliotecario0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bibliotecario0.get(), HttpStatus.OK);
    }
            
    @PostMapping("/bibliotecarios")
    public ResponseEntity<Bibliotecario> saveProduct(@RequestBody @Valid Bibliotecario bibliotecario){
        return new ResponseEntity<>(bibliotecarioReporitory.save(bibliotecario),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/bibliotecarios/{id}")
    public ResponseEntity<?>
            deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<Bibliotecario> product0 
                = (bibliotecarioReporitory.findById(id));
        if (product0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bibliotecarioReporitory.delete(product0.get());
        return new ResponseEntity<>(HttpStatus.OK);  
    }
    @PutMapping("/bibliotecarios/{id}")
    public ResponseEntity<Bibliotecario> update(@PathVariable(value = "id") UUID id,
                    @RequestBody @Valid Bibliotecario bibliotecario) {
        Optional<Bibliotecario> bibliotecario0 = bibliotecarioReporitory.findById(id);
        if (bibliotecario0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bibliotecario.setId(id);
        return new ResponseEntity<>(bibliotecarioReporitory.save(bibliotecario), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

