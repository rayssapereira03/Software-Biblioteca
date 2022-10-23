package com.example.biblioja.Controller;

import com.example.biblioja.model.Livro;
import com.example.biblioja.repository.LivroRepository;
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
public class LivroControler {
     @Autowired
    LivroRepository livroReporitory;

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> getAliProducts() {
        return new ResponseEntity<>(livroReporitory.findAll(), HttpStatus.OK);

    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Livro>
            getOneProduts(@PathVariable(value = "id") UUID id) {
        Optional<Livro> livro0 = (livroReporitory.findById(id));
        if (livro0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livro0.get(), HttpStatus.OK);
    }
            
    @PostMapping("/livros")
    public ResponseEntity<Livro> saveProduct(@RequestBody @Valid Livro livro){
        return new ResponseEntity<>(livroReporitory.save(livro),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/livros/{id}")
    public ResponseEntity<?>
            deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<Livro> product0 
                = (livroReporitory.findById(id));
        if (product0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        livroReporitory.delete(product0.get());
        return new ResponseEntity<>(HttpStatus.OK);  
    }
    @PutMapping("/livros/{id}")
    public ResponseEntity<Livro> update(@PathVariable(value = "id") UUID id,
                    @RequestBody @Valid Livro livro) {
        Optional<Livro> livro0 = livroReporitory.findById(id);
        if (livro0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        livro.setId(id);
        return new ResponseEntity<>(livroReporitory.save(livro), HttpStatus.OK);
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
