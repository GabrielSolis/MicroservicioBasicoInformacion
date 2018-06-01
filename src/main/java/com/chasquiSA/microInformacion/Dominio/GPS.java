package com.chasquiSA.microInformacion.Dominio;

public class GPS {
	private int codigo;
	private String codigoReferencia;
	
	public GPS() {
		
	}
	public GPS(int codigo, String codigoReferencia) {
		
		this.codigo = codigo;
		this.codigoReferencia = codigoReferencia;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	
	
}
