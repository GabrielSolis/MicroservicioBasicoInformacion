package com.chasquiSA.microInformacion.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private static Connection conexion=null;
	public static Connection getConexion() throws Exception{
		//String urlDataBase = "jdbc:postgresql://localhost:5432/ChasquiSA";
		String urlDataBase = "jdbc:postgresql://localhost/ChasquiSA";
		//String urlDataBase = "postgres://@ec2-54-243-185-195.compute-1.amazonaws.com:5432/dehidnnhrpsn52";
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(urlDataBase,"postgres","1234");
			//conexion = DriverManager.getConnection(urlDataBase,"bjwhihtocljkeh","31dfb838b17298bdd936a7893ed6ab64fab55381c52677b1858ec7a12c5ac598");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
}
