package com.vitor.desafioviceri.services;

import com.vitor.desafioviceri.entities.Tarefa;
import com.vitor.desafioviceri.entities.enums.Prioridade;
import com.vitor.desafioviceri.repositories.TarefaRepository;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import com.vitor.desafioviceri.services.exceptions.ResourceNotFoundException;
import com.vitor.desafioviceri.services.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    private final TarefaMapper mapper;

    public TarefaDTO inserir(TarefaDTO dto){
        return persistir(dto);
    }

    public void deletar(Long id){
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }
    }

    public TarefaDTO atualizar(TarefaDTO dto){
        if (!Objects.nonNull(dto.getId())){
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }
        return persistir(dto);
    }

    public TarefaDTO concluirTarefa(Long id){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        tarefa.setConcluida(true);
        tarefa = repository.save(tarefa);
        return mapper.toDto(tarefa);
    }

    public Page<TarefaDTO> buscarTarefasPendentes(Prioridade prioridade, Pageable pageable){
        Page<Tarefa> page = repository.buscarTarefasPendentes(prioridade, pageable);
        return page.map(mapper::toDto);
    }

    private TarefaDTO persistir(TarefaDTO dto){
        Tarefa entidade = mapper.toEntity(dto);
        entidade = repository.save(entidade);
        return mapper.toDto(entidade);
    }

}
