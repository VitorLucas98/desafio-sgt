package com.vitor.desafioviceri.web.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends StandardError{

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(LocalDate timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void adicionaErro(String fieldName, String message) {
        erros.add(new FieldMessage(fieldName, message));
    }

}
