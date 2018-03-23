package com.chasquiSA.microInformacion.Dominio;

public class Persona {
	private int codigo;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String sexo;
	private String direccion;
	private String dni;
	private String fechaNacimiento;
	private String celular;
	private String estadoCivil;
	
	public Persona() {
		
	}


	
	public Persona(int codigo, String nombres, String apellidoPaterno, String apellidoMaterno, String correo,
			String sexo, String direccion, String dni, String fechaNacimiento, String celular, String estadoCivil) {
		super();
		this.codigo = codigo;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correo = correo;
		this.sexo = sexo;
		this.direccion = direccion;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.celular = celular;
		this.estadoCivil = estadoCivil;
	}



	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getSexo() {
		return sexo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public boolean validar(String nombres, String apellidoPaterno, String apellidoMaterno, String correo,
			String direccion, String dni, String fechaNacimiento, String celular) {
		if(nombres == "" || apellidoPaterno == "" || apellidoMaterno == "" || correo == "" || direccion == ""
				|| dni == "" || fechaNacimiento =="" || celular =="") {
			return false;
		}
		return true;
	}
	
}
