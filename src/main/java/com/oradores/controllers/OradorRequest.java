package com.oradores.controllers;

public class OradorRequest {
	private String nombre;
	private String apellido;
	private String tema;
	private Long id;

	// porque lo necesita jackson
	public OradorRequest() {

	}

	public OradorRequest(String nombre, String apellido, String tema) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tema = tema;
	}

	public OradorRequest(Long id, String nombre, String apellido, String tema) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tema = tema;

	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTema() {
		return tema;
	}

	@Override
	public String toString() {
		return "OradorRequest [nombre=" + nombre + ", apellido=" + apellido + ", tema=" + tema + "]";
	}

}
