package com.joaosilveira.challengeauthsecurity.dto;

// Esse objeto vai carregar o nome do compo e mensagem do erro do BeanValidation
public class FieldMessage {

    // ex: campo name
    private String fieldName;

    // ex: tem que ser entre 5 e 60 caracteres
    private String message;


    public FieldMessage() {

    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
