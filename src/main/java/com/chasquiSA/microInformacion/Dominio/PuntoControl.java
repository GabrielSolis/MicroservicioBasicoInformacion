package com.chasquiSA.microInformacion.Dominio;


public class PuntoControl {
	private int codigo;
	private double latitud;
	private double longitud;
	private String direccion;
	private String punto2;
	private boolean vigencia;
	
	
	public PuntoControl() {
		
	}



	public PuntoControl(int codigo, double latitud, double longitud, String direccion, String punto2,
			 boolean vigencia) {
		
		this.codigo = codigo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.direccion = direccion;
		this.punto2 = punto2;
		this.vigencia = vigencia;
	}


	
	public String getPunto2() {
		return punto2;
	}



	public void setPunto2(String punto2) {
		this.punto2 = punto2;
	}



	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
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
