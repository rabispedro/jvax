package com.jvax.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.jvax.controllers.interfaces.ICrudController;
import com.jvax.daos.DoacaoDao;
import com.jvax.models.DoacaoModel;

public class DoacaoController implements ICrudController<Long, DoacaoModel> {
	private final DoacaoDao doacaoDao;

	public DoacaoController(DoacaoDao doacaoDao) {
		this.doacaoDao = doacaoDao;
	}
	
	@Override
	public DoacaoModel cadastro(DoacaoModel model) {
		try {
			return this.doacaoDao.cadastro(model);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public DoacaoModel pesquisaPorCodigo(Long key) {
		try {
			return this.doacaoDao.pesquisaPorCodigo(key);
		} catch (Exception e) {
			return null;
		}
	}

	public List<DoacaoModel> pesquisaPorCodigoDoador(Long key) {
		try {
			return this.doacaoDao.pesquisaPorCodigoDoador(key);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<DoacaoModel> pesquisaPorNomeDoador(String nome) {
		try {
			return this.doacaoDao.pesquisaPorNomeDoador(nome);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<DoacaoModel> pesquisaPorCpfDoador(String cpf) {
		try {
			return this.doacaoDao.pesquisaPorCpfDoador(cpf);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<DoacaoModel> pesquisaPorDataInicial(LocalDate inicio) {
		try {
			return this.doacaoDao.pesquisaPorDataInicial(inicio);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<DoacaoModel> pesquisaPorDataInicialEFinal(LocalDate inicio, LocalDate fim) {
		try {
			return this.doacaoDao.pesquisaPorDataInicialEFinal(inicio, fim);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public List<DoacaoModel> pesquisaPorDataFinal(LocalDate fim) {
		try {
			return this.doacaoDao.pesquisaPorDataFinal(fim);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public DoacaoModel alteracao(Long key, DoacaoModel model) {
		throw new NoSuchMethodError("Não há implementação para método: alteracao");
	}
	
	@Override
	public boolean remocao(Long key) {
		throw new NoSuchMethodError("Não há implementação para método: alteracao");
	}
}
