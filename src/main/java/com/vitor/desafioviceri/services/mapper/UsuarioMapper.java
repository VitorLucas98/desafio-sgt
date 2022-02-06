package com.vitor.desafioviceri.services.mapper;

import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.services.dtos.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {

}
