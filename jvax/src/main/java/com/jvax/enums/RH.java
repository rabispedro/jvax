package com.jvax.enums;

public enum RH {
	POSITIVO(true, "POSITIVO"),
	NEGATIVO(false, "NEGATIVO"),
	DESCONHECIDO(null, "DESCONHECIDO");

	private final Boolean valor;
	private final String descricao;

	private RH(Boolean valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Boolean getValor() {
		return this.valor;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public static RH toEnum(String descricao) {
		if (descricao == null) {
			return RH.DESCONHECIDO;
		}

		for(var rh: RH.values()) {
			if (rh.getDescricao().equalsIgnoreCase(descricao)) {
				return rh;
			}
		}
		
		return RH.DESCONHECIDO;
	}

	public static RH toEnum(Boolean valor) {
		if (valor == null) {
			return RH.DESCONHECIDO;
		}

		for(var rh: RH.values()) {
			if (rh.getValor().equals(valor)) {
				return rh;
			}
		}
		
		return RH.DESCONHECIDO;
	}
}
