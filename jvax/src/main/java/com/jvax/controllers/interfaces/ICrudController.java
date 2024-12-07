package com.jvax.controllers.interfaces;

public interface ICrudController<K, M> {
	M cadastro(M model);
	M pesquisaPorCodigo(K key);
	M alteracao(K key, M model);
	boolean remocao(K key);
}
