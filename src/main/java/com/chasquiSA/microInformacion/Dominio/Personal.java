package com.chasquiSA.microInformacion.Dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personal extends Persona{
	String fechaRegistro;
	String fechaRetiro;
	String cargo;
	boolean vigencia;
	
	public Personal() {
		
	}
	public Personal(String fechaRegistro, String fechaRetiro, String cargo, boolean vigencia) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.fechaRetiro = fechaRetiro;
		this.cargo = cargo;
		this.vigencia = vigencia;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	

	public void asignarFechaRetiro(){
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("YYYY-LL-dd");
		this.fechaRetiro = formato.format(fechaActual);
	}
}
