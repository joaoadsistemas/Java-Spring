package com.joaosilveira.dscatalog.dtos;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String err, String path) {
        super(timestamp, status, err, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    // Método para adicionar um erro a lista de errors
    public void addError(String fieldName, String message) {
        FieldMessage fieldError = new FieldMessage(fieldName, message);
        errors.add(fieldError);
    }
}
