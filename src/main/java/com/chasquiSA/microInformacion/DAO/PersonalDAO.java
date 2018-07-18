package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.chasquiSA.microInformacion.Dominio.Personal;



public class PersonalDAO {
//	
//	@Autowired
//	private DataSource dataSource;
	public void registrarPersonal(Personal personal)throws Exception{
		try {
	
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iPersonal(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstm.setString(1,personal.getNombres());
			cstm.setString(2,personal.getApellidoPaterno());
			cstm.setString(3,personal.getApellidoMaterno());
			cstm.setString(4,personal.getCorreo());
			cstm.setString(5,personal.getDireccion());
			cstm.setString(6,personal.getDni());
			cstm.setString(7,personal.getSexo());
			cstm.setString(8,personal.getFechaNacimiento());
			cstm.setString(9,personal.getCelular());
			cstm.setString(10,personal.getEstadoCivil());
			cstm.setString(11,personal.getFechaRegistro());
			cstm.setString(12,personal.getCargo());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	
	public boolean verificarDNI(String dni) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_ComprobarDNI(?)}");
			
			ResultSet rs;
			cstm.setString(1,dni);
			rs = cstm.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch(Exception e) {
			throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	
	public void modificarPersonal(Personal personal)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aPersonal(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstm.setInt(1,personal.getCodigo());;
			cstm.setString(2,personal.getNombres());
			cstm.setString(3,personal.getApellidoPaterno());
			cstm.setString(4,personal.getApellidoMaterno());
			cstm.setString(5,personal.getCorreo());
			cstm.setString(6,personal.getDireccion());
			cstm.setString(7,personal.getDni());
			cstm.setString(8,personal.getSexo());
			cstm.setString(9,personal.getFechaNacimiento());
			cstm.setString(10,personal.getCelular());
			cstm.setString(11,personal.getEstadoCivil());
			cstm.setString(12,personal.getFechaRegistro());
			cstm.setString(13,personal.getCargo());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<Personal> listar(String estado, String apellido)throws Exception{
		List<Personal> listaPersonal = new ArrayList<>();
		try {
			//
			//Connection conexion = dataSource.getConnection();
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liPersonalEstadoApellido(?,?)}");
			
			ResultSet rs;
			cstm.setString(1,estado);
			cstm.setString(2,apellido);
			rs = cstm.executeQuery();
			while(rs.next()) {
			
				Personal personal = new Personal();
				personal.setNombres(rs.getString("nombres"));
				personal.setApellidoPaterno(rs.getString("apellidopaterno"));
				personal.setApellidoMaterno(rs.getString("apellidomaterno"));
				personal.setDni(rs.getString("dni"));
				personal.setCargo(rs.getString("cargo"));
				personal.setCodigo(rs.getInt("codigoPersona"));
				personal.setVigencia(rs.getBoolean("vigencia"));
				listaPersonal.add(personal);
			}
			
			return listaPersonal;
		}catch(Exception e) {
			throw e;
		}finally{
			listaPersonal = null;
			Conexion.cerrarConexion();
		}

	}
	
	public void modificarEstado(Personal personal)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aPersonalEstado(?,?,?)}");
			cstm.setInt(1,personal.getCodigo());;
			cstm.setBoolean(2,personal.isVigencia());
			cstm.setString(3,personal.getFechaRetiro());
			
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	
	public Personal getPersonal(int id) throws Exception {
		Personal personal = new Personal();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lPersonal(?)}");
			ResultSet rs;
			cstm.setInt(1,id);
			rs = cstm.executeQuery();
			
			while(rs.next()) {
				personal.setNombres(rs.getString("nombres"));
				personal.setApellidoPaterno(rs.getString("apellidopaterno"));
				personal.setApellidoMaterno(rs.getString("apellidomaterno"));
				personal.setSexo(rs.getString("sexo"));
				personal.setDni(rs.getString("dni"));
				personal.setCelular(rs.getString("celular"));
				personal.setCodigo(rs.getInt("codigoPersona"));
				personal.setCorreo(rs.getString("correo"));
				personal.setDireccion(rs.getString("direccion"));
				personal.setCargo(rs.getString("cargo"));
				personal.setFechaNacimiento(rs.getString("fechanacimiento"));
				personal.setFechaRegistro(rs.getString("fecharegistro"));	
				personal.setFechaRetiro(rs.getString("fecharetiro"));
				personal.setEstadoCivil(rs.getString("estadocivil"));
				personal.setVigencia(rs.getBoolean("vigencia"));
			}
			Conexion.cerrarConexion();
			return personal;
		}catch(Exception e) {
			throw e;
		}finally{
			personal = null;
			
		}
	}
}
