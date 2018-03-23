package com.chasquiSA.microInformacion.Dominio;

public class CalleRuta {
	private int codigo;
	private String direccion;
	private Double latitud; //Latitud del punto de inicio de la calle
	private Double longitud;
	private int numeroOrden;
	private boolean vigencia;
	
	public CalleRuta() {
		
	}
	
	public CalleRuta(int codigo, String direccion, Double latitud, Double longitud, int numeroOrdem, boolean vigencia) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.numeroOrden = numeroOrdem;
		this.vigencia = vigencia;
	}

	public int getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}


	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public boolean isVigencia() {
		return vigencia;
	}
	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	
	public void obtenerPunto(String punto) {
		String datos[];
		punto = punto.replaceAll("\\(","");
		punto = punto.replaceAll("\\)","");
		datos = punto.split(",");
		this.latitud = Double.parseDouble(datos[0]);
		this.longitud = Double.parseDouble(datos[1]);
	}
	public void validarDireccion() {
		this.direccion = this.direccion.replaceAll(",", " ");
	}
	
	
	
}
