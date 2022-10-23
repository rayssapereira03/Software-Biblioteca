package com.example.biblioja.Controller;

import com.example.biblioja.model.Usuario;
import com.example.biblioja.repository.UsuarioRepository;
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
public class UsuarioControler {

    @Autowired
    UsuarioRepository usuarioReporitory;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAliProducts() {
        return new ResponseEntity<>(usuarioReporitory.findAll(), HttpStatus.OK);

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario>
            getOneProduts(@PathVariable(value = "id") UUID id) {
        Optional<Usuario> usuario0 = (usuarioReporitory.findById(id));
        if (usuario0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario0.get(), HttpStatus.OK);
    }
            
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> saveProduct(@RequestBody @Valid Usuario usuario){
        return new ResponseEntity<>(usuarioReporitory.save(usuario),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?>
            deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<Usuario> product0 
                = (usuarioReporitory.findById(id));
        if (product0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioReporitory.delete(product0.get());
        return new ResponseEntity<>(HttpStatus.OK);  
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> update(@PathVariable(value = "id") UUID id,
                    @RequestBody @Valid Usuario usuario) {
        Optional<Usuario> usuario0 = usuarioReporitory.findById(id);
        if (usuario0.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(id);
        return new ResponseEntity<>(usuarioReporitory.save(usuario), HttpStatus.OK);
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
