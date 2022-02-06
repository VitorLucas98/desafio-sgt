package com.vitor.desafioviceri.services.dtos;

import com.vitor.desafioviceri.entities.enums.Prioridade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Preenchimento obrigatório")
    private String descricao;

    @NotBlank(message = "Preenchimento obrigatório")
    private Prioridade prioridade;

    private boolean concluida = false;

    private UsuarioDTO usuario;
}
