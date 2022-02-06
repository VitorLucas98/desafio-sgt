package com.vitor.desafioviceri.entities.enums;

public enum Prioridade {
	ALTA(0,"Alta"),
	MEDIA(1,"Média"),
	BAIXA(2, "Baixa");

	private Integer cod;
	private String descricao;

	Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static String convertePrioridadeString(Integer cod){
		if (cod == null) {
			return null;
		}
		for (Prioridade x : Prioridade.values()) {
			if (cod.equals(x.getCod())) {
				return x.descricao;
			}
		}
		throw new IllegalArgumentException("Prioridade inválida!" + cod);
	}

}
