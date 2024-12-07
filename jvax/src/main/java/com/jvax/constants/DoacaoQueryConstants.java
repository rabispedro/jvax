package com.jvax.constants;

public class DoacaoQueryConstants {
	private DoacaoQueryConstants() {}

	public static final String SELECT_QUERY = """
		SELECT
			d1.codigo,
			d1.data,
			d1.hora,
			d1.volume,
			d1.situacao,
			d2.codigo,
			d2.nome,
			d2.cpf,
			d2.contato,
			d2.situacao,
			d2.tipo_sanguineo,
			d2.rh
		FROM doacao d1
		INNER JOIN doador d2
			ON d1.doador_codigo = d2.codigo
		WHERE
			d1.situacao = TRUE
		""";

	public static final String INSERT_QUERY = """
		INSERT INTO doacao(data, hora, volume, situacao, doador_codigo)
		VALUES (?, ?, ?, ?, ?);
		""";
}
