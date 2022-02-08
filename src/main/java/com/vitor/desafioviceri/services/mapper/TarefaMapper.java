package com.vitor.desafioviceri.services.mapper;

import com.vitor.desafioviceri.entities.Tarefa;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @Mapping(source = "idUsuario", target = "usuario.id")
    Tarefa toEntity(TarefaDTO dto);

    @Override
    @InheritInverseConfiguration
    TarefaDTO toDto(Tarefa entity);
}
