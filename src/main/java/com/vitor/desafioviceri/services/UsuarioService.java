package com.vitor.desafioviceri.services;

import javax.transaction.Transactional;

import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.services.dtos.UsuarioDTO;
import com.vitor.desafioviceri.services.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import com.vitor.desafioviceri.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;

	private final UsuarioMapper mapper;

	public UsuarioDTO inserir(UsuarioDTO usuarioDTO){
		Usuario entidade = mapper.toEntity(usuarioDTO);
		entidade = repository.save(entidade);
		return mapper.toDto(entidade);
	}

}
