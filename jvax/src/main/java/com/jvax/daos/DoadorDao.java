package com.jvax.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jvax.constants.DoadorQueryConstants;
import com.jvax.daos.interfaces.ICrudDao;
import com.jvax.enums.RH;
import com.jvax.enums.Situacao;
import com.jvax.enums.TipoSanguineo;
import com.jvax.models.DoadorModel;

public class DoadorDao implements ICrudDao<Long, DoadorModel> {
	private final Connection connection;

	public DoadorDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public DoadorModel cadastro(DoadorModel model) throws SQLException {
		StringBuilder sb = new StringBuilder(DoadorQueryConstants.INSERT_QUERY);

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, model.getNome());
		pstmt.setString(2, model.getCpf());
		pstmt.setString(3, model.getContato());
		pstmt.setBoolean(4, model.getSituacao().getValor());
		pstmt.setString(5, model.getTipoSanguineo().getValor());
		pstmt.setBoolean(6, model.getRh().getValor());

		int results = pstmt.executeUpdate();

		if (results == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				model.setCodigo(rs.getLong(1));
			} else {
				System.out.println("Não foi possível pegar a chave do doador inserido");
			}
			rs.close();
		} else {
			System.out.println("Não foi possível inserir o doador");
		}
		pstmt.close();

		return model;
	}

	@Override
	public DoadorModel pesquisaPorCodigo(Long key) throws SQLException {
		DoadorModel model = null;

		StringBuilder sb = new StringBuilder(DoadorQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d.codigo = ?;
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

	public List<DoadorModel> pesquisaPorNome(String nome) throws SQLException {
		List<DoadorModel> models = new ArrayList<>();

		StringBuilder sb = new StringBuilder(DoadorQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d.nome ILIKE '%?%';
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setString(1, nome);

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DoadorModel model = parseSqlModel(rs);
			models.add(model);
		}
		rs.close();
		pstmt.close();

		return models;
	}

	public DoadorModel pesquisaPorCpf(String cpf) throws SQLException {
		DoadorModel model = null;

		StringBuilder sb = new StringBuilder(DoadorQueryConstants.SELECT_QUERY);
		sb.append("""
			AND d.cpf ILIKE ?;
			""");

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setString(1, cpf);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			model = parseSqlModel(rs);
		}
		rs.close();
		pstmt.close();

		return model;
	}

	@Override
	public DoadorModel alteracao(DoadorModel model) throws SQLException {
		StringBuilder sb = new StringBuilder(DoadorQueryConstants.UPDATE_QUERY);

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setString(1, model.getNome());
		pstmt.setString(2, model.getCpf());
		pstmt.setString(3, model.getContato());
		pstmt.setBoolean(4, model.getSituacao().getValor());
		pstmt.setString(5, model.getTipoSanguineo().getValor());
		pstmt.setBoolean(6, model.getRh().getValor());
		pstmt.setLong(7, model.getCodigo());

		int result = pstmt.executeUpdate();
		pstmt.close();

		if (result == 1) {
			return model;
		}

		return null;
	}

	@Override
	public boolean remocao(Long key) throws SQLException {
		StringBuilder sb = new StringBuilder(DoadorQueryConstants.DELETE_QUERY);

		PreparedStatement pstmt = this.connection.prepareStatement(sb.toString());
		pstmt.setLong(1, key);
		
		int result = pstmt.executeUpdate();
		pstmt.close();

		return (result == 1);
	}

	private DoadorModel parseSqlModel(ResultSet rs) throws SQLException {
		return new DoadorModel(
			Long.valueOf(rs.getLong(1)),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4),
			Situacao.toEnum(rs.getBoolean(5)),
			TipoSanguineo.toEnum(rs.getString(6)),
			RH.toEnum(rs.getBoolean(7)));
	}
}
