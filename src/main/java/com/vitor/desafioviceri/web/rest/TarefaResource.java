package com.vitor.desafioviceri.web.rest;


import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.entities.enums.Prioridade;
import com.vitor.desafioviceri.repositories.UsuarioRepository;
import com.vitor.desafioviceri.services.TarefaService;
import com.vitor.desafioviceri.services.UsuarioService;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/tarefas")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService service;

    @PostMapping
    public ResponseEntity<TarefaDTO> inserir(@RequestBody @Valid TarefaDTO dto,  @AuthenticationPrincipal String emailUsuario){
        dto = service.inserir(dto, emailUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, @AuthenticationPrincipal String emailUsuario){
        service.deletar(id, emailUsuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@RequestBody @Valid TarefaDTO dto, @AuthenticationPrincipal String emailUsuario){
        dto = service.atualizar(dto, emailUsuario);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/concluir-tarefa/{id}")
    public ResponseEntity<TarefaDTO> concluirTarefa(@PathVariable Long id, @AuthenticationPrincipal String emailUsuario){
        TarefaDTO dto = service.concluirTarefa(id, emailUsuario);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> buscarTarefasPendentes(Prioridade prioridade, @AuthenticationPrincipal String emailUsuario, Pageable pageable){
        Page<TarefaDTO> page = service.buscarTarefasPendentes(prioridade, emailUsuario, pageable);
        return ResponseEntity.ok(page);
    }

}
