package com.jvax.models;

import com.jvax.enums.RH;
import com.jvax.enums.Situacao;
import com.jvax.enums.TipoSanguineo;
import com.jvax.models.abstracts.BaseModel;

public class DoadorModel extends BaseModel {
	private String nome;
	private String cpf;
	private String contato;
	private boolean tipoERhCorretos = false;
	private Situacao situacao;
	private TipoSanguineo tipoSanguineo;
	private RH rh;

	public DoadorModel() {
		super();
	}

	public DoadorModel(
		Long codigo,
		String nome,
		String cpf,
		String contato,
		Situacao situacao,
		TipoSanguineo tipoSanguineo,
		RH rh) {

		super(codigo);

		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		this.situacao = situacao;
		this.tipoSanguineo = tipoSanguineo;
		this.rh = rh;
	}

	public DoadorModel(
		String nome,
		String cpf,
		String contato,
		Situacao situacao,
		TipoSanguineo tipoSanguineo,
		RH rh) {

		this.codigo = null;
		this.nome = nome;
		this.cpf = cpf;
		this.contato = contato;
		this.situacao = situacao;
		this.tipoSanguineo = tipoSanguineo;
		this.rh = rh;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public boolean isTipoERhCorretos() {
		return tipoERhCorretos;
	}

	public void setTipoERhCorretos(boolean tipoERhCorretos) {
		this.tipoERhCorretos = tipoERhCorretos;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public RH getRh() {
		return rh;
	}

	public void setRh(RH rh) {
		this.rh = rh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + (tipoERhCorretos ? 1231 : 1237);
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tipoSanguineo == null) ? 0 : tipoSanguineo.hashCode());
		result = prime * result + ((rh == null) ? 0 : rh.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DoadorModel other = (DoadorModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (tipoERhCorretos != other.tipoERhCorretos)
			return false;
		if (situacao != other.situacao)
			return false;
		if (tipoSanguineo != other.tipoSanguineo)
			return false;
		return (rh == other.rh);
	}

	@Override
	public String toString() {
		return "{ 'codigo': '" +
			codigo +
			"', 'nome': '" +
			nome +
			"', 'cpf': '" +
			cpf +
			"', 'contato': '" +
			contato +
			"', 'tipoERhCorretos': '" +
			tipoERhCorretos +
			"', 'situacao': '" +
			situacao.getDescricao() +
			"', 'tipoSanguineo': '" +
			tipoSanguineo.getDescricao() +
			"', 'rh': '" +
			rh.getDescricao() +
			"' }";
	}
}
