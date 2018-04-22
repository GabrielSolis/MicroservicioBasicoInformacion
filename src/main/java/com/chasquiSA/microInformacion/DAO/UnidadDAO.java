package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chasquiSA.microInformacion.Dominio.RegistroUnidad;
import com.chasquiSA.microInformacion.Dominio.socioPlaca;


public class UnidadDAO {
	public void registrarUnidad(RegistroUnidad rUnidad)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iUnidad(?,?,?,?,?,?,?,?)}");
			ResultSet rs;
			int codigoUnidad; 
			cstm.setString(1,rUnidad.getUnidad().getPlaca());
			cstm.setString(2,rUnidad.getUnidad().getMarca());
			cstm.setString(3,rUnidad.getUnidad().getModelo());
			cstm.setInt(4,rUnidad.getUnidad().getCapacidad());
			cstm.setInt(5,rUnidad.getUnidad().getAnioFabricacion());
			cstm.setString(6,rUnidad.getUnidad().getColor());
			cstm.setString(7,rUnidad.getUnidad().getEstado());
			cstm.setBoolean(8,rUnidad.getUnidad().asignarVigencia());
			rs = cstm.executeQuery();
			rs.next();
			codigoUnidad = rs.getInt(1);
			
			CallableStatement cstmU = conexion.prepareCall("{call pr_iregistrounidad(?,?,?,?,?)}");
			cstmU.setInt(1, rUnidad.getSocio().getCodigo());
			cstmU.setInt(2, codigoUnidad);
			cstmU.setString(3,rUnidad.getFechaRegistro());
			cstmU.setString(4,rUnidad.getFechaRegistro());
			cstmU.setBoolean(5, rUnidad.asignarVigencia());
			//System.out.println(rs.getInt(1));
			cstmU.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	public void modificarNumeroAccionesSocio(RegistroUnidad rUnidad)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			System.out.println(rUnidad.getSocio().getNumeroAcciones() + " - " + rUnidad.getSocio().getCodigo());
			
			CallableStatement cstm = conexion.prepareCall("{call pr_aSocioAcciones(?,?)}");
			cstm.setInt(1,rUnidad.getSocio().getCodigo());;
			cstm.setInt(2,rUnidad.getSocio().getNumeroAcciones());
			
		
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
//	public void registrarUnidad(Unidad unidad)throws Exception{
//		try {
//			Connection conexion = Conexion.getConexion();
//			CallableStatement cstm = conexion.prepareCall("{call pr_iunidad(?,?,?,?,?,?,?,?,?)}");
//			
//			cstm.setString(1,unidad.getPlaca());
//			cstm.setString(2,unidad.getSoat());
//			cstm.setString(3,unidad.getMarca());
//			cstm.setString(4,unidad.getModelo());
//			cstm.setInt(5,unidad.getCapacidad());
//			cstm.setInt(6,unidad.getAnioFabricacion());
//			cstm.setString(7,unidad.getColor());
//			cstm.setString(8,unidad.getEstado());
//			cstm.setBoolean(9,unidad.isVigencia());
//			cstm.execute();
//			Conexion.cerrarConexion();
//		}catch(Exception e) {
//				throw e;
//		}finally{
//			Conexion.cerrarConexion();
//		}
//	}
	
	public List<socioPlaca> listar(String estado, String apellido)throws Exception{
		List<socioPlaca> sociosPlacas = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			//CallableStatement cstm = conexion.prepareCall("{call pr_liPlacasSocios()}");
			CallableStatement cstm = conexion.prepareCall("{call pr_liPlacasSocios(?,?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
		
			cstm.setString(1,estado);
			cstm.setString(2,apellido);
			rs = cstm.executeQuery();
			//rs = sts.executeQuery("Select *from socio");
			//boolean valor;
			//valor = rs.next();
			while(rs.next()) {
				socioPlaca socioPlaca = new socioPlaca();
				//registroUnidad.setCodigo(rs.getInt("codigoRegistroUnidad"));
				socioPlaca.setCodigoSocio(rs.getInt("p_codigoSocio"));
				socioPlaca.setNombreCompleto(rs.getString("p_nombresCompletos"));
				socioPlaca.setPlacas(rs.getString("p_unidades"));
				socioPlaca.setEstado(rs.getString("p_estadoUnidad"));
				
				//socio.setApellidoPaterno(rs.getString("apellidoPaterno"));
				sociosPlacas.add(socioPlaca);
				//valor =rs.next();
			}
			Conexion.cerrarConexion();
			return sociosPlacas;
		}catch(Exception e) {
			throw e;
		}finally{
			sociosPlacas = null;
			Conexion.cerrarConexion();
		}

	}

//	public List<RegistroUnidad> listar(String estado, String apellido)throws Exception{
//		List<RegistroUnidad> registroUnidades = new ArrayList<>();
//		try {
//			Connection conexion = Conexion.getConexion();
//			CallableStatement cstm = conexion.prepareCall("{call pr_liUnidadesEstadoApellido(?,?)}");
//			//Statement sts = conexion.createStatement();
//			ResultSet rs;
//		
//			cstm.setString(1,estado);
//			cstm.setString(2,apellido);
//			rs = cstm.executeQuery();
//			//rs = sts.executeQuery("Select *from socio");
//			//boolean valor;
//			//valor = rs.next();
//			while(rs.next()) {
//				RegistroUnidad registroUnidad = new RegistroUnidad();
//				registroUnidad.setCodigo(rs.getInt("codigoRegistroUnidad"));
//				registroUnidad.getSocio().setCodigo(rs.getInt("codigoSocio"));
//				registroUnidad.getSocio().setNombres(rs.getString("nombres"));
//				registroUnidad.getSocio().setApellidoPaterno(rs.getString("apellidoPaterno"));
//				registroUnidad.getSocio().setApellidoMaterno(rs.getString("apellidoMaterno"));
//				registroUnidad.getUnidad().setPlaca(rs.getString("placa"));
//				registroUnidad.getUnidad().setEstado(rs.getString("estado"));
//				registroUnidad.getSocio().setNumeroAcciones(rs.getInt("numeroAcciones"));
//				
//				//socio.setApellidoPaterno(rs.getString("apellidoPaterno"));
//				registroUnidades.add(registroUnidad);
//				//valor =rs.next();
//			}
//			Conexion.cerrarConexion();
//			return registroUnidades;
//		}catch(Exception e) {
//			throw e;
//		}finally{
//			registroUnidades = null;
//			Conexion.cerrarConexion();
//		}
//
//	}
	public boolean verificarPlaca(String placa) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_ComprobarPlaca(?)}");
			
			ResultSet rs;
			cstm.setString(1,placa);
			rs = cstm.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch(Exception e) {
			throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	public void modificarVigencia(RegistroUnidad rUnidad)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			System.out.println(rUnidad.getCodigo());
			
			CallableStatement cstm = conexion.prepareCall("{call pr_aRegistroUnidadVigencia(?,?,?)}");
			cstm.setInt(1,rUnidad.getCodigo());;
			cstm.setBoolean(2,rUnidad.isVigencia());
			cstm.setString(3,rUnidad.getFechaRetiro());
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	public void modificarRegistroUnidad(RegistroUnidad registroUnidad)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
//			System.out.println(registroUnidad.getApellidoMaterno() + " " + socio.getApellidoMaterno() + " " + socio.getCelular()+ " " + socio.getCodigo() + " " + socio.getCorreo() 
//			+ " " + socio.getEstadoCivil() + " " + socio.getDireccion() + " " + socio.getFechaRegistro() + " " +socio.getNombresApellidosPariente() + " " + socio.getTelefonoPariente());
//			
			CallableStatement cstm = conexion.prepareCall("{call pr_aRegistroUnidad(?,?,?,?,?,?,?,?,?,?)}");
			cstm.setInt(1,registroUnidad.getCodigo());
			cstm.setInt(2,registroUnidad.getSocio().getCodigo());
			cstm.setString(3, registroUnidad.getFechaRegistro());
			cstm.setString(4,registroUnidad.getUnidad().getPlaca());
			cstm.setString(5,registroUnidad.getUnidad().getMarca());
			cstm.setString(6,registroUnidad.getUnidad().getModelo());
			cstm.setInt(7,registroUnidad.getUnidad().getCapacidad());
			cstm.setInt(8,registroUnidad.getUnidad().getAnioFabricacion());
			cstm.setString(9,registroUnidad.getUnidad().getColor());
			cstm.setString(10,registroUnidad.getUnidad().getEstado());
		
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	public RegistroUnidad getRegistroUnidad(int id) throws Exception {
		RegistroUnidad registroUnidad = new RegistroUnidad();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lRegistroUnidades(?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
			cstm.setInt(1,id);
			rs = cstm.executeQuery();
			
			while(rs.next()) {
				
				registroUnidad.setFechaRegistro(rs.getString("fecharegistro"));
				registroUnidad.getSocio().setCodigo(rs.getInt("codigoSocio"));
				registroUnidad.getSocio().setNombres(rs.getString("nombres"));
				registroUnidad.getSocio().setApellidoPaterno(rs.getString("apellidoPaterno"));
				registroUnidad.getSocio().setApellidoMaterno(rs.getString("apellidoMaterno"));
				registroUnidad.getSocio().setNumeroAcciones(rs.getInt("numeroAcciones"));
				registroUnidad.getUnidad().setCodigo(rs.getInt("codigoUnidad"));
				registroUnidad.getUnidad().setPlaca(rs.getString("placa"));
				registroUnidad.getUnidad().setMarca(rs.getString("marca"));
				registroUnidad.getUnidad().setModelo(rs.getString("modelo"));
				registroUnidad.getUnidad().setColor(rs.getString("color"));
				registroUnidad.getUnidad().setCapacidad(rs.getInt("capacidad"));
				registroUnidad.getUnidad().setAnioFabricacion(rs.getInt("anioFabricacion"));
				registroUnidad.getUnidad().setEstado(rs.getString("estado"));
				
			}
			Conexion.cerrarConexion();
			return registroUnidad;
		}catch(Exception e) {
			throw e;
		}finally{
			registroUnidad = null;
			Conexion.cerrarConexion();
		}
	}
	public RegistroUnidad getRegistroUnidadPlaca(String placa) throws Exception {
		RegistroUnidad registroUnidad = new RegistroUnidad();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lRegistroUnidadPlaca(?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
			cstm.setString(1,placa);
			rs = cstm.executeQuery();
			
			while(rs.next()) {
				
				//System.out.println(rs.getString("fecharegistro"));
				registroUnidad.setCodigo(rs.getInt("codigo"));
				registroUnidad.setFechaRegistro(rs.getString("fecharegistro"));
				registroUnidad.getSocio().setCodigo(rs.getInt("codigoSocio"));
				registroUnidad.getSocio().setNombres(rs.getString("nombres"));
				registroUnidad.getSocio().setApellidoPaterno(rs.getString("apellidoPaterno"));
				registroUnidad.getSocio().setApellidoMaterno(rs.getString("apellidoMaterno"));
				registroUnidad.getSocio().setNumeroAcciones(rs.getInt("numeroAcciones"));
				registroUnidad.getUnidad().setCodigo(rs.getInt("codigoUnidad"));
				registroUnidad.getUnidad().setPlaca(rs.getString("placa"));
				registroUnidad.getUnidad().setMarca(rs.getString("marca"));
				registroUnidad.getUnidad().setModelo(rs.getString("modelo"));
				registroUnidad.getUnidad().setColor(rs.getString("color"));
				registroUnidad.getUnidad().setCapacidad(rs.getInt("capacidad"));
				registroUnidad.getUnidad().setAnioFabricacion(rs.getInt("anioFabricacion"));
				registroUnidad.getUnidad().setEstado(rs.getString("estado"));
				
			}
			Conexion.cerrarConexion();
			return registroUnidad;
		}catch(Exception e) {
			throw e;
		}finally{
			registroUnidad = null;
			Conexion.cerrarConexion();
		}
	}
	public List<RegistroUnidad> listarUnidadesSocio(String estado,int idSocio) throws Exception {
		List<RegistroUnidad> registroUnidades = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liUnidadesSocio(?,?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
			cstm.setString(1,estado);
			cstm.setInt(2,idSocio);
			rs = cstm.executeQuery();
			
			while(rs.next()) {
				RegistroUnidad registroUnidad = new RegistroUnidad();
				registroUnidad.setCodigo(rs.getInt("codigo"));
				registroUnidad.setFechaRegistro(rs.getString("fecharegistro"));
				registroUnidad.getSocio().setCodigo(rs.getInt("codigoSocio"));
				registroUnidad.getSocio().setNombres(rs.getString("nombres"));
				registroUnidad.getSocio().setApellidoPaterno(rs.getString("apellidoPaterno"));
				registroUnidad.getSocio().setApellidoMaterno(rs.getString("apellidoMaterno"));
				registroUnidad.getSocio().setNumeroAcciones(rs.getInt("numeroAcciones"));
				registroUnidad.getUnidad().setCodigo(rs.getInt("codigoUnidad"));
				registroUnidad.getUnidad().setPlaca(rs.getString("placa"));
				registroUnidad.getUnidad().setMarca(rs.getString("marca"));
				registroUnidad.getUnidad().setModelo(rs.getString("modelo"));
				registroUnidad.getUnidad().setColor(rs.getString("color"));
				registroUnidad.getUnidad().setCapacidad(rs.getInt("capacidad"));
				registroUnidad.getUnidad().setAnioFabricacion(rs.getInt("anioFabricacion"));
				registroUnidad.getUnidad().setEstado(rs.getString("estado"));
				
				registroUnidades.add(registroUnidad);
			}
			Conexion.cerrarConexion();
			return registroUnidades;
		}catch(Exception e) {
			throw e;
		}finally{
			registroUnidades = null;
			Conexion.cerrarConexion();
		}
	}
}

