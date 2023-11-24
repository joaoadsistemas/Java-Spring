package com.joaosilveira.crudchallange.dto;

import javax.swing.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {


    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
