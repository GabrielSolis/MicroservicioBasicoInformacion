package com.chasquiSA.microInformacion.Dominio;

public class socioPlaca {
	private int codigoSocio;
	private String nombreCompleto;
	private String placas;
	private String estado;
	
	public socioPlaca() {
		
	}

	public socioPlaca(int codigoSocio, String nombreCompleto, String placas, String estado) {
		super();
		this.codigoSocio = codigoSocio;
		this.nombreCompleto = nombreCompleto;
		this.placas = placas;
		this.estado = estado;
	}

	public int getCodigoSocio() {
		return codigoSocio;
	}

	public void setCodigoSocio(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPlacas() {
		return placas;
	}

	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
