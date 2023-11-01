package com.remedios.joaosilveira.ProjetoRemedios.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratador404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratador400(MethodArgumentNotValidException e) {
        var erros = e.getFieldErrors();



        return ResponseEntity.badRequest().body(erros.stream().map(DadosErro::new).toList());
    }

    public record DadosErro(
            String campo,
            String msg
    ) {

        public DadosErro(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }

    }

}
