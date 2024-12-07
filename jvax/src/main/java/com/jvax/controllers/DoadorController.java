package com.jvax.controllers;

import java.util.Collections;
import java.util.List;

import com.jvax.controllers.interfaces.ICrudController;
import com.jvax.daos.DoadorDao;
import com.jvax.models.DoadorModel;

public class DoadorController implements ICrudController<Long, DoadorModel>{
	private final DoadorDao doadorDao;

	public DoadorController(DoadorDao doadorDao) {
		this.doadorDao = doadorDao;
	}

	@Override
	public DoadorModel cadastro(DoadorModel model) {
		try {
			return this.doadorDao.cadastro(model);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public DoadorModel pesquisaPorCodigo(Long key) {
		try {
			return this.doadorDao.pesquisaPorCodigo(key);
		}
		catch (Exception e) {
			return null;
		}
	}

	public List<DoadorModel> pesquisaPorNome(String nome) {
		try {
			return this.doadorDao.pesquisaPorNome(nome);
		}
		catch (Exception e) {
			return Collections.emptyList();
		}
	}

	public DoadorModel pesquisaPorCpf(String cpf) {
		try {
			return this.doadorDao.pesquisaPorCpf(cpf);
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public DoadorModel alteracao(Long key, DoadorModel model) {
		try {
			var doador = this.doadorDao.pesquisaPorCodigo(key);

			if (doador == null) {
				return null;
			}
			
			doador.setNome(model.getNome());
			doador.setCpf(model.getCpf());
			doador.setContato(model.getContato());
			doador.setSituacao(model.getSituacao());
			doador.setTipoSanguineo(model.getTipoSanguineo());
			doador.setRh(model.getRh());

			doador = this.doadorDao.alteracao(doador);

			return doador;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean remocao(Long key) {
		try {
			return this.doadorDao.remocao(key);
		} catch (Exception e) {
			return false;
		}
	}
}
