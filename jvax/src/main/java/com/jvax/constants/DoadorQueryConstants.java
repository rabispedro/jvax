package com.jvax.constants;

public class DoadorQueryConstants {
	private DoadorQueryConstants() {}

	public static final String SELECT_QUERY = """
		SELECT
			d.codigo,
			d.nome,
			d.cpf,
			d.contato,
			d.situacao,
			d.tipo_sanguineo,
			d.rh
		FROM doador d
		WHERE
			d.situacao = TRUE
		""";

	public static final String INSERT_QUERY = """
		INSERT INTO doador(nome, cpf, contato, situacao, tipo_sanguineo, rh)
		VALUES (?, ?, ?, ?, ?, ?);
		""";

	public static final String DELETE_QUERY = """
		UPDATE doador
			SET situacao = FALSE
		WHERE codigo = ?;
		""";

	public static final String UPDATE_QUERY = """
		UPDATE doador
			SET nome = ?,
			SET cpf = ?,
			SET contato = ?,
			SET situacao = ?,
			SET tipo_sanguineo = ?,
			SET rh = ?
		WHERE codigo = ?;
		""";
}
