package com.jvax.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.jvax.constants.DoacaoQueryConstants;
import com.jvax.daos.interfaces.ICrudDao;
import com.jvax.enums.RH;
import com.jvax.enums.Situacao;
import com.jvax.enums.TipoSanguineo;
import com.jvax.models.DoacaoModel;
import com.jvax.models.DoadorModel;

public class DoacaoDao implements ICrudDao<Long, DoacaoModel> {
	private final Connection connection;

	public DoacaoDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public DoacaoModel cadastro(DoacaoModel model) throws SQLException {
		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.INSERT_QUERY);

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

		pstmt.setDate(1, Date.valueOf(model.getData()));
		pstmt.setTime(2, Time.valueOf(model.getHora()));
		pstmt.setDouble(3, model.getVolume());
		pstmt.setBoolean(4, model.getSituacao().getValor());
		pstmt.setLong(5, model.getDoador().getCodigo());

		int results = pstmt.executeUpdate();
		if (results == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				model.setCodigo(rs.getLong(1));
			} else {
				System.out.println("Não foi possível pegar a chave da doação inserida");
			}
			rs.close();
		} else {
			System.out.println("Não foi possível inserir a doação");
		}
		pstmt.close();

		return model;
	}

	@Override
	public DoacaoModel pesquisaPorCodigo(Long key) throws SQLException {
		DoacaoModel model = null;

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d1.codigo = ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setLong(1, key);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			model = parseSqlModel(rs);
		}
		rs.close();
		pstmt.close();

		return model;
	}

	public List<DoacaoModel> pesquisaPorCodigoDoador(Long key) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d1.doador_codigo = ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setLong(1, key);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public List<DoacaoModel> pesquisaPorNomeDoador(String nome) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d2.nome ILIKE '%?%';
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setString(1, nome);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public List<DoacaoModel> pesquisaPorCpfDoador(String cpf) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d2.cpf ILIKE ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setString(1, cpf);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public List<DoacaoModel> pesquisaPorDataInicial(LocalDate inicio) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d1.data < ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setDate(1, Date.valueOf(inicio));

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public List<DoacaoModel> pesquisaPorDataInicialEFinal(LocalDate inicio, LocalDate fim) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d1.data BETWEEN ? AND ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setDate(1, Date.valueOf(inicio));
		pstmt.setDate(2, Date.valueOf(fim));

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public List<DoacaoModel> pesquisaPorDataFinal(LocalDate fim) throws SQLException {
		List<DoacaoModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoacaoQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d1.data > ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setDate(1, Date.valueOf(fim));

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoacaoModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	/**
	 * @deprecated (2024, Doacao não pode sofrer alteração, verifique outras
	 *             soluções)
	 */
	@Deprecated
	@Override
	public DoacaoModel alteracao(DoacaoModel model) {
		return null;
	}

	/**
	 * @deprecated (2024, Doacao não pode sofrer remoção, verifique outras soluções)
	 */
	@Deprecated
	@Override
	public boolean remocao(Long key) throws SQLException {
		return false;
	}

	private DoacaoModel parseSqlModel(ResultSet rs) throws SQLException {
		DoadorModel doador = new DoadorModel(
			Long.valueOf(rs.getLong(6)),
			rs.getString(7),
			rs.getString(8),
			rs.getString(9),
			Situacao.toEnum(rs.getBoolean(10)),
			TipoSanguineo.toEnum(rs.getString(11)),
			RH.toEnum(rs.getBoolean(12)));

		return new DoacaoModel(
			Long.valueOf(rs.getLong(1)),
			LocalDate.parse(rs.getDate(2).toString()),
			LocalTime.parse(rs.getTime(3).toString()),
			rs.getDouble(4),
			Situacao.toEnum(rs.getBoolean(5)),
			doador);
	}
}
