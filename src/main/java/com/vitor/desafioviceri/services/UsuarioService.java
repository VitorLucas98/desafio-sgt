package com.vitor.desafioviceri.services;

import javax.transaction.Transactional;

import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.services.dtos.UsuarioDTO;
import com.vitor.desafioviceri.services.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.desafioviceri.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private final UsuarioRepository repository;

	private final UsuarioMapper mapper;

	public UsuarioDTO inserir(UsuarioDTO usuarioDTO){
		Usuario entidade = mapper.toEntity(usuarioDTO);
		entidade.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		entidade = repository.save(entidade);
		return mapper.toDto(entidade);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null){
			throw new UsernameNotFoundException("Email invalido");
		}
		return usuario;
	}


}
