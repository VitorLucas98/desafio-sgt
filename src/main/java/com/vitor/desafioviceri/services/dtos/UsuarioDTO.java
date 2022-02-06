package com.vitor.desafioviceri.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Preenchimento invalido")
    private String nome;

    @Email(message = "Email inválido!")
    private String email;

    @NotBlank(message = "Preenchimento invalido")
    private String senha;
}
