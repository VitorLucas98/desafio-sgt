package com.vitor.desafioviceri.web.rest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {

    private LocalDate timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

}
