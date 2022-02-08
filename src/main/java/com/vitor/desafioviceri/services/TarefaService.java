package com.vitor.desafioviceri.services;

import com.vitor.desafioviceri.entities.Tarefa;
import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.entities.enums.Prioridade;
import com.vitor.desafioviceri.repositories.TarefaRepository;
import com.vitor.desafioviceri.repositories.UsuarioRepository;
import com.vitor.desafioviceri.services.dtos.TarefaDTO;
import com.vitor.desafioviceri.services.exceptions.ResourceNotFoundException;
import com.vitor.desafioviceri.services.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.function.Function;

@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    private final TarefaMapper mapper;

    private final UsuarioRepository usuarioRepository;

    public TarefaDTO inserir(TarefaDTO dto, String email) {
        return persistir(dto, email);
    }

    public void deletar(Long id, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        repository.findById(id).ifPresentOrElse(
                tarefa -> {
                    if (tarefa.getUsuario().equals(usuario)) {
                        repository.deleteById(id);
                    }
                }, () -> {
                    throw new ResourceNotFoundException("Tarefa não encontrada");
                });
    }

    public TarefaDTO atualizar(TarefaDTO dto, String email) {
        if (!Objects.nonNull(dto.getId()) || !repository.findById(dto.getId()).isPresent()) {
            throw new ResourceNotFoundException("Tarefa não encontrada");
        }
        return persistir(dto, email);
    }

    public TarefaDTO concluirTarefa(Long id, String email) {
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        tarefa.setConcluida(true);
        return persistir(mapper.toDto(tarefa), email);
    }

    public Page<TarefaDTO> buscarTarefasPendentes(Prioridade prioridade, String email, Pageable pageable) {
        Page<Tarefa> page = repository.buscarTarefasPendentes(prioridade, email, pageable);
        return page.map(mapper::toDto);
    }

    private TarefaDTO persistir(TarefaDTO dto, String email) {
        Tarefa entidade = mapper.toEntity(dto);
        Usuario usuario = usuarioRepository.findByEmail(email);
        entidade.setUsuario(usuario);
        entidade = repository.save(entidade);
        return mapper.toDto(entidade);
    }

}
