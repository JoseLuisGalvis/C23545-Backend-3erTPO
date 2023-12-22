package com.oradores.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.oradores.connection.AdministradorDeConexiones;
import com.oradores.entity.Orador;


public class MySQLOradorRepository implements OradorRepository {

	@Override
	public void save(Orador orador) {
		String sql = "insert into oradores (nombre, apellido, tema, fechaAlta) values(?,?,?,?)";
		Connection conn = AdministradorDeConexiones.getConnection();

		try {
			// sql injection!!!
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// cargar los ? con los valores
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setDate(4, new java.sql.Date(System.currentTimeMillis()));

			statement.executeUpdate();// INSERT,UPDATE,DELETE

			// Codigo Video 31 min 53:30
			ResultSet res = statement.getGeneratedKeys();
			if(res.next()) {
				Long id = res.getLong(1);
				orador.setId(id);
			}


			// Agregar esta línea
			System.out.println("El orador ha sido Creado Exitosamente");
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador", e);
		}
	}


	// Metodo getById
	@Override
	public Orador getById(Long id) {
		String sql = "SELECT id, nombre, apellido, tema, fechaAlta FROM oradores WHERE id = ?";

		Connection conn = AdministradorDeConexiones.getConnection();

		Orador orador = null;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();// SELECT

			// hay datos?
			if (res.next()) {
				// obtengo los datos desde el ResultSet
				Long _id = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				Date fechaAlta = res.getDate(5);

				orador = new Orador(_id, nombre, apellido, tema, LocalDate.now());
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}
		return orador;
	}

	// Metodo Update
	@Override
	public void update(Orador orador) {
		String sql = "update oradores " + "set nombre=?, apellido=?,  tema=?" + "where id = ?";

		// try with resource
		try (Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setLong(4, orador.getId());

			statement.executeUpdate();
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo actualizar el orador", e);
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM oradores WHERE ID = ?";

		Connection conn = AdministradorDeConexiones.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();// INSERT,UPDATE,DELETE

			// Agregar esta línea
			System.out.println("El orador ha sido eliminado exitosamente");
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el orador", e);
		}
	}

	// Metodo FindAll
	@Override
	public List<Orador> findAll() {
		String sql = "select id, nombre, apellido, tema, fechaAlta from oradores";

		List<Orador> oradores = new ArrayList<>();// ver bien power en Spring!

		try (Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);

			ResultSet res = statement.executeQuery();// SELECT

			// hay datos?
			while (res.next()) {
				// obtengo los datos desde el ResultSet
				Long _id = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				Date fechaAlta = res.getDate(5);

				Orador orador = new Orador(_id, nombre, apellido, tema, LocalDate.now());/* tph fechaAlta de java.sql.Date a LocalDate */
				oradores.add(orador);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}

		return oradores;
	}

}