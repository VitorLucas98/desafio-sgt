package com.vitor.desafioviceri.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.vitor.desafioviceri.entities.enums.Prioridade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	private Prioridade prioridade;

	private boolean concluida;
	
	@ManyToOne
	private Usuario usuario;

}
