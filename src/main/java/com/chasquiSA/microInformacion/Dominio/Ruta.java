package com.chasquiSA.microInformacion.Dominio;

import java.util.List;

public class Ruta {
	private int codigo;
	private String letra;
	private String estado;
	private int tiempo;
	private List<CalleRuta> calles;
	private boolean vigencia;
	
	public Ruta() {
		
	}


	
	public Ruta(int codigo, String letra, String estado, int tiempo, List<CalleRuta> calles, boolean vigencia) {
		super();
		this.codigo = codigo;
		this.letra = letra;
		this.estado = estado;
		this.tiempo = tiempo;
		this.calles = calles;
		this.vigencia = vigencia;
	}



	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public List<CalleRuta> getCalles() {
		return calles;
	}
	
	public void setCalles(List<CalleRuta> calles) {
		this.calles = calles;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	 
	
}
