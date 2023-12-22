package com.oradores.repository;

import java.time.LocalDate;
import java.util.List;

import com.oradores.entity.Orador;

public class MainOradorRepository {

	public static void main(String[] args) {
		// interface i = new ClaseQueImplementa()..

		OradorRepository repository = new MySQLOradorRepository();

		// Metodo Save
		Orador nuevo = new Orador("Mila", "Kunis", "Linux", LocalDate.now());
		repository.save(nuevo);

		// Metodo GetById
		Orador oradorAConsultar = repository.getById(1L);
		System.out.println(oradorAConsultar);

		// Metodo Update
		// Obtener el orador que se desea actualizar (por ejemplo, mediante el método
		// getById)
		Orador oradorAActualizar = repository.getById(4L); // suponiendo que el ID 3 existe en la base de datos
		System.out.println(oradorAActualizar);

		// Actualizar los valores del orador
		oradorAActualizar.setNombre("Carla");
		oradorAActualizar.setApellido("Muñoz");
		oradorAActualizar.setTema("Javascript");

		// Llamar al método update() para actualizar el orador en la base de datos
		repository.update(oradorAActualizar);

		System.out.println(oradorAActualizar);

		// Metodo Delete
		repository.delete(5L);

		// Metodo FindAll
		List<Orador> oradores = repository.findAll();
		mostrarOradoresEnColumnas(oradores);

	}

	public static void mostrarOradoresEnColumnas(List<Orador> oradores) {
		oradores.stream().forEach(orador -> {
			System.out.println("ID: " + orador.getId());
			System.out.println("Nombre: " + orador.getNombre());
			System.out.println("Apellido: " + orador.getApellido());
			System.out.println("Tema: " + orador.getTema());
			System.out.println("Fecha de Alta: " + orador.getFechaAlta());
			System.out.println("-----------------------------");
		});
	}

}


