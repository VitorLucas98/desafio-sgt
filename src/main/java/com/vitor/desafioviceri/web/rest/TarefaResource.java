package com.vitor.desafioviceri.web.rest;

import com.vitor.desafioviceri.entities.enums.Prioridade;
import com.vitor.desafioviceri.services.TarefaService;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TarefaDTO> inserir(@RequestBody @Valid TarefaDTO dto){
        dto = service.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@RequestBody @Valid TarefaDTO dto){
        dto = service.atualizar(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/concluir-tarefa/{id}")
    public ResponseEntity<TarefaDTO> concluirTarefa(@PathVariable Long id){
        TarefaDTO dto = service.concluirTarefa(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> buscarTarefasPendentes(Prioridade prioridade, Pageable pageable){
        Page<TarefaDTO> page = service.buscarTarefasPendentes(prioridade, pageable);
        return ResponseEntity.ok(page);
    }

}
