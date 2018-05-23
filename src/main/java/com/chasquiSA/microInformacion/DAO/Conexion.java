package com.chasquiSA.microInformacion.DAO;

//import java.net.URI;
//import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Conexion {
	private static Connection conexion=null;
	public static Connection getConexion() throws Exception{
		String urlDataBase = "jdbc:postgresql://ec2-174-129-28-38.compute-1.amazonaws.com:5432/d7ite1rtejj9da";
		try {
			Class.forName("org.postgresql.Driver");
			//conexion = DriverManager.getConnection(urlDataBase,user,clave);
			}catch(Exception e) {
			throw e;
		}
		return conexion;
	}

	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
	
	
}
