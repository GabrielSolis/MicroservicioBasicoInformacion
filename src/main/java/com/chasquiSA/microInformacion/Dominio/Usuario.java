package com.chasquiSA.microInformacion.Dominio;

public class Usuario {
	int codigo;
	Personal personal;
	String usuario;
	String clave;
	String cargo;
	String claveAntigua;
	int indicador;
	boolean vigencia;
	
	public Usuario() {
		
	}
	
	
	public Usuario(int codigo, String cargo,Personal personal, String usuario, String clave, int indicador, boolean vigencia) {
		super();
		this.codigo = codigo;
		this.personal = personal;
		this.usuario = usuario;
		this.clave = clave;
		this.indicador = indicador;
		this.cargo = cargo;
		this.vigencia = vigencia;
	}

	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getClaveAntigua() {
		return claveAntigua;
	}


	public void setClaveAntigua(String claveAntigua) {
		this.claveAntigua = claveAntigua;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getIndicador() {
		return this.indicador;
	}
	public void setIndicador(int indicador) {
		this.indicador = indicador;
	}
	public Personal getPersonal() {
		return personal;
	}
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isVigencia() {
		return vigencia;
	}
	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	
	
}
