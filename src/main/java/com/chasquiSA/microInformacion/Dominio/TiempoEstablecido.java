package com.chasquiSA.microInformacion.Dominio;

public class TiempoEstablecido {
	int codigo;
	Ruta ruta;
	PuntoControl punto;
	int tiempoEstablecido;
	int orden;
	boolean vigencia;
	
	
	public TiempoEstablecido() {
		
	}
	public TiempoEstablecido(int codigo,Ruta ruta, PuntoControl punto, int tiempoEstablecido, int orden,boolean vigencia) {
		super();
		this.codigo = codigo;
		this.ruta = ruta;
		this.punto = punto;
		this.tiempoEstablecido = tiempoEstablecido;
		this.orden = orden;
		this.vigencia = vigencia;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public PuntoControl getPunto() {
		return punto;
	}
	public void setPunto(PuntoControl punto) {
		this.punto = punto;
	}
	public int getTiempoEstablecido() {
		return tiempoEstablecido;
	}
	public void setTiempoEstablecido(int tiempoEstablecido) {
		this.tiempoEstablecido = tiempoEstablecido;
	}
	
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public boolean isVigencia() {
		return vigencia;
	}
	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	
	
}
