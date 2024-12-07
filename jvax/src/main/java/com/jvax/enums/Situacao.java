package com.jvax.enums;

public enum Situacao {
	ATIVO(true, "ATIVO"),
	INATIVO(false, "INATIVO");
	
	private final Boolean valor;
	private final String descricao;

	private Situacao(Boolean valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Boolean getValor() {
		return this.valor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static Situacao toEnum(String descricao) {
		if (descricao == null) {
			return Situacao.INATIVO;
		}

		for(var situacao: Situacao.values()) {
			if (situacao.getDescricao().equalsIgnoreCase(descricao)) {
				return situacao;
			}
		}

		return Situacao.INATIVO;
	}

	public static Situacao toEnum(Boolean valor) {
		if (valor == null) {
			return Situacao.INATIVO;
		}

		for(var situacao: Situacao.values()) {
			if (situacao.getValor().equals(valor)) {
				return situacao;
			}
		}

		return Situacao.INATIVO;
	}
}
