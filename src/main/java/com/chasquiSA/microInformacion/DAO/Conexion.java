package com.chasquiSA.microInformacion.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private static Connection conexion=null;
	public static Connection getConexion() throws Exception{
		String urlDataBase = "jdbc:postgresql://localhost:5432/ChasquiSA";
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(urlDataBase,"postgres","1234");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
}
