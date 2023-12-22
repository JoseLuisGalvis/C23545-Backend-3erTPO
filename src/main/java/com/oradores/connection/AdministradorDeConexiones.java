package com.oradores.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {

	public static Connection getConnection() {

		// min 32:40
		// logica para crear la conexion a la db

		// abrir una conexion a la db
		// java.sql.Connection > conexion fisica contra la db
		String host = "localhost";
		String username = "root";
		String password = "YrsaBello6662$";
		String port = "3306";
		String dbName = "backend3tpo";

		// String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName +
		// "?serverTimeZone=UTC&useSSL=false";
		String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName
				+ "?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
		String driver = "com.mysql.cj.jdbc.Driver";

		try { // se ve en el avanzado!!
			Class.forName(driver);
			System.out.println("Estoy Conectado a la BBDD");
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener conexion: " + dbUrl + " - " + driver);
		}

	}
}
