package com.vitor.desafioviceri.services.mapper;

import com.vitor.desafioviceri.entities.Tarefa;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {
}
