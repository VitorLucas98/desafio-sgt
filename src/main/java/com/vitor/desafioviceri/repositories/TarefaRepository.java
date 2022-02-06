package com.vitor.desafioviceri.repositories;

import com.vitor.desafioviceri.entities.Tarefa;
import com.vitor.desafioviceri.entities.enums.Prioridade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT ta FROM Tarefa ta "
            + "WHERE ta.concluida = false and (ta.prioridade = :prioridade OR :prioridade IS NULL)")
    Page<Tarefa> buscarTarefasPendentes(@Param("prioridade") Prioridade prioridade, Pageable pageable);

}
