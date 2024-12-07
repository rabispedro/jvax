package com.jvax.daos.interfaces;

import java.sql.SQLException;

public interface ICrudDao<K, M> {
	M cadastro(M model) throws SQLException;
	M pesquisaPorCodigo(K key) throws SQLException;
	M alteracao(M model) throws SQLException;
	boolean remocao(K key) throws SQLException;
}
