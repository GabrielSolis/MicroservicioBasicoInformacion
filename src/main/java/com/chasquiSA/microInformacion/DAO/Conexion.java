package com.chasquiSA.microInformacion.DAO;

//import java.net.URI;
//import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

public class Conexion {
	private static Connection conexion=null;
	public static Connection getConexion() throws Exception{
		//String urlDataBase = "jdbc:postgresql://localhost:5432/ChasquiSA";
		//String urlDataBase = "jdbc:postgresql://localhost/ChasquiSA";
		String urlDataBase = "jdbc:postgresql://ec2-174-129-28-38.compute-1.amazonaws.com:5432/d7ite1rtejj9da";
		try {
			Class.forName("org.postgresql.Driver");
			//conexion = DriverManager.getConnection(urlDataBase,"postgres","1234");
			conexion = DriverManager.getConnection(urlDataBase,"epnvssgbxirdfv","09588d1b6b0a2b62a99276551999d9a5e9bebe9f3c2a04b0e926a11066b51289");
		}catch(Exception e) {
			throw e;
		}
		return conexion;
	}
	/* public static Connection getConexion() throws URISyntaxException, SQLException {
	        URI dbUri = new URI(System.getenv("DATABASE_URL"));

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
	        
	        return DriverManager.getConnection(dbUrl, username, password);
	    }*/
	public static void cerrarConexion() throws Exception{
		conexion.close();
	}
	
	/*public static Connection getConexion() throws Exception{
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
	}*/
}
