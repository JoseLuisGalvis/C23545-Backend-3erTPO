package com.oradores.entity;

import java.time.LocalDate;

public class Orador {
	
	/* Atributos */
	
	private Long id;
	private String nombre;
	private String apellido;
	private String tema;
	private LocalDate fechaAlta;
	
	/* Constructor para enviar un orador a la BBDD*/
	public Orador(String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
	}
	
	/* Constructor para traer un orador desde la BBDD*/
	public Orador(Long id, String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre != null) {
			this.nombre = nombre;
		} else {
			this.nombre = "N/D";
		}
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	// Polimorfismo con Redefinicion
	@Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}

}
