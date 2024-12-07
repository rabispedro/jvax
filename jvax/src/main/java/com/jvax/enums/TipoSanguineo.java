package com.jvax.enums;

public enum TipoSanguineo {
	A("a", "A"),
	B("b", "B"),
	AB("ab", "AB"),
	O("o", "O"),
	DESCONHECIDO(null, "DESCONHECIDO");

	private final String valor;
	private final String descricao;

	private TipoSanguineo(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return this.valor;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static TipoSanguineo toEnum(String descricao) {
		if (descricao == null) {
			return TipoSanguineo.DESCONHECIDO;
		}
		
		for(var tipoSanguineo: TipoSanguineo.values()) {
			if (tipoSanguineo.getDescricao().equalsIgnoreCase(descricao)) {
				return tipoSanguineo;
			}
		}
		
		return TipoSanguineo.DESCONHECIDO;
	}
}
