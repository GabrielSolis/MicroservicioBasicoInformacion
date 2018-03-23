package com.chasquiSA.microInformacion.Dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroUnidad {
	private int codigo;
	private String fechaRegistro;
	private String fechaRetiro;
	private Socio socio;
	private Unidad unidad;
	private boolean vigencia;
	
	public RegistroUnidad() {
		super();
		this.unidad = new Unidad();
		this.socio = new Socio();
	}

	
	public RegistroUnidad(int codigo, String fechaRegistro, String fechaRetiro, Socio socio, Unidad unidad,
			boolean vigencia) {
		super();
		this.codigo = codigo;
		this.fechaRegistro = fechaRegistro;
		this.fechaRetiro = fechaRetiro;
		this.socio = socio;
		this.unidad = unidad;
		this.vigencia = vigencia;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public String getFechaRetiro() {
		return fechaRetiro;
	}


	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}


	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}


	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}




	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	
	public boolean asignarVigencia() {
		return true;
	}
	public void asignarFechaRetiro(){
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("YYYY-LL-DD");
		this.fechaRetiro = formato.format(fechaActual);
	}
	
}

