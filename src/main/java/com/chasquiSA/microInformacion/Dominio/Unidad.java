package com.chasquiSA.microInformacion.Dominio;



public class Unidad {
	private int codigo;
	private String placa;
	private String marca;
	private String modelo;
	private int capacidad;
	private int anioFabricacion;
	private String color;
	private String estado;
	private boolean vigencia;
	
	public Unidad() {
		
	}

	public Unidad(int codigo, String placa, String marca, String modelo, int capacida,
			int anioFabricacion, String color, String estado, boolean vigencia) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.capacidad = capacida;
		this.anioFabricacion = anioFabricacion;
		this.color = color;
		this.estado = estado;
		this.vigencia = vigencia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacida) {
		this.capacidad = capacida;
	}

	public int getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(int anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
//	public void asignarEstado() {
//		this.estado = "A";
//	}
	
}

