package com.joaosilveira.empregadosauthchallenge.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private Instant timestamp;
    private Integer status;
    private String err;
    private String path;

    private List<FieldMessage> errors = new ArrayList<>();


    public ValidationError(Instant timestamp, Integer status, String err, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.err = err;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErr() {
        return err;
    }

    public String getPath() {
        return path;
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
