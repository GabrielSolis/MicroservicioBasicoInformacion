package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chasquiSA.microInformacion.Dominio.PuntoControl;

public class PuntoControlDAO {
	public int registrarPunto(PuntoControl punto) throws Exception {
		int valor;
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_iPuntoControl(?,?,?)}");
			ResultSet rs;
			cstm.setDouble(1,punto.getLatitud());
			cstm.setDouble(2,punto.getLongitud());
			cstm.setString(3,punto.getDireccion());
			rs =cstm.executeQuery();
			rs.next();
			valor = rs.getInt(1);
			Conexion.cerrarConexion();
			return valor;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<PuntoControl> listarPuntos()throws Exception{
		List<PuntoControl> lista = new ArrayList<>();
		ResultSet rs;
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liPuntosControlVigentes()}");
			rs = cstm.executeQuery();
			while(rs.next()) {
				PuntoControl punto = new PuntoControl();
				punto.setCodigo(rs.getInt("p_codigo"));
				punto.setDireccion(rs.getString("p_direccion"));
				punto.setVigencia(rs.getBoolean("p_vigencia"));
				punto.obtenerPunto(rs.getString("p_punto"));
				punto.setPunto2(rs.getString("p_punto2"));
				//System.out.println(rs.getString("p_punto"));
				lista.add(punto);
			}
			Conexion.cerrarConexion();
			return lista;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<PuntoControl> listarPuntosEstado(String estado) throws Exception{
		List<PuntoControl> lista = new ArrayList<>();
		ResultSet rs;
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liPuntosControl(?)}");
			cstm.setString(1,estado);
			rs = cstm.executeQuery();
			while(rs.next()) {
				PuntoControl punto = new PuntoControl();
				punto.setCodigo(rs.getInt("p_codigo"));
				punto.setDireccion(rs.getString("p_direccion"));
				punto.setVigencia(rs.getBoolean("p_vigencia"));
				punto.obtenerPunto(rs.getString("p_punto"));
				punto.setPunto2(rs.getString("p_punto2"));
				lista.add(punto);
			}
			Conexion.cerrarConexion();
			return lista;
		}catch(Exception e){
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public void cambiarEstado(PuntoControl punto) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aPuntoControlVigencia(?,?)}");
			cstm.setInt(1,punto.getCodigo());
			cstm.setBoolean(2,punto.isVigencia());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public void modificarPunto(PuntoControl punto) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aPuntoControl(?,?,?,?,?,?)}");
			cstm.setInt(1,punto.getCodigo());
			cstm.setDouble(2,punto.getLatitud());
			cstm.setDouble(3,punto.getLongitud());
			cstm.setString(4,punto.getDireccion());
			cstm.setString(5,punto.getPunto2());
			cstm.setBoolean(6,punto.isVigencia());
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
