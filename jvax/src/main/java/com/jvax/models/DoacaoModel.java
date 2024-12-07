package com.jvax.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jvax.enums.Situacao;
import com.jvax.models.abstracts.BaseModel;

public class DoacaoModel extends BaseModel {
	private LocalDate data;
	private LocalTime hora;
	private double volume;
	private Situacao situacao;
	private DoadorModel doador;

	public DoacaoModel() {
		super();
	}

	public DoacaoModel(
		Long codigo,
		LocalDate data,
		LocalTime hora,
		double volume,
		Situacao situacao,
		DoadorModel doador) {

		super(codigo);

		this.data = data;
		this.hora = hora;
		this.volume = volume;
		this.situacao = situacao;
		this.doador = doador;
	}

	public DoacaoModel(
		LocalDate data,
		LocalTime hora,
		double volume,
		Situacao situacao,
		DoadorModel doador) {
			
		this.codigo = null;	
		this.data = data;
		this.hora = hora;
		this.volume = volume;
		this.situacao = situacao;
		this.doador = doador;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public DoadorModel getDoador() {
		return doador;
	}

	public void setDoador(DoadorModel doador) {
		this.doador = doador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		long temp;
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((doador == null) ? 0 : doador.hashCode());
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
		DoacaoModel other = (DoacaoModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (Double.doubleToLongBits(volume) != Double.doubleToLongBits(other.volume))
			return false;
		if (situacao != other.situacao)
			return false;
		if (doador == null) {
			if (other.doador != null)
				return false;
		} else if (!doador.equals(other.doador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{ 'codigo': '" +
			codigo +
			"', 'data': '" +
			data +
			"', 'hora': '" +
			hora +
			"', 'volume': '" +
			volume +
			"', 'situacao': '" +
			situacao.getDescricao() +
			"', 'doador': '" +
			doador.toString() +
			"' }";
	}
}
