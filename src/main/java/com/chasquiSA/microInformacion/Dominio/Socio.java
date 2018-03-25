package com.chasquiSA.microInformacion.Dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Socio extends Persona {

	int numeroAcciones;
	String fechaRegistro;
	String fechaRetiro;
	String nombresApellidosPariente;
	String estado;
	String telefonoPariente;
	boolean vigencia;
	
	public Socio() {
		
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}
	public String getTelefonoPariente() {
		return this.telefonoPariente;
	}
	public void setTelefonoPariente(String telefono) {
		this.telefonoPariente = telefono;
	}
	
	

	public Socio(int numeroAcciones, String fechaRegistro, String fechaRetiro, String nombresApellidosPariente,
			String estado, String telefonoPariente, boolean vigencia) {
		super();
		this.numeroAcciones = numeroAcciones;
		this.fechaRegistro = fechaRegistro;
		this.fechaRetiro = fechaRetiro;
		this.nombresApellidosPariente = nombresApellidosPariente;

		this.estado = estado;
		this.telefonoPariente = telefonoPariente;
		this.vigencia = vigencia;
	}

	public String getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	public int getNumeroAcciones() {
		return numeroAcciones;
	}
	public void setNumeroAcciones(int numeroAcciones) {
		this.numeroAcciones = numeroAcciones;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getNombresApellidosPariente() {
		return nombresApellidosPariente;
	}
	
	public void setNombresApellidosPariente(String nombresApellidosPariente) {
		this.nombresApellidosPariente = nombresApellidosPariente;
		
	}
	
	public boolean isVigencia() {
		return vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	public void asignarFechaRetiro(){
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("YYYY-LL-DD");
		System.out.println("Desde el DOMINIO" +formato.format(fechaActual) );
		this.fechaRetiro = formato.format(fechaActual);
	}
	public void asignarEstado() {
		this.estado = "A";
	}
	public void aumentarNumeroAcciones() {
		this.numeroAcciones=this.numeroAcciones+23;
	}
	public void desminuirNumeroAcciones() {
		this.numeroAcciones=this.numeroAcciones-23;
	}
}
