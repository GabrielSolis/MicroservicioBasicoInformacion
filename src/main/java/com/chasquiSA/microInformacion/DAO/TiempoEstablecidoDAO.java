package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chasquiSA.microInformacion.Dominio.PuntoControl;
import com.chasquiSA.microInformacion.Dominio.Ruta;
import com.chasquiSA.microInformacion.Dominio.TiempoEstablecido;

public class TiempoEstablecidoDAO {
	public void  registrarTiempoEstabelcido(TiempoEstablecido tiempoEstablecido)throws Exception{
	
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_iTiempoEstablecido(?,?,?,?,?)}");
			stm.setInt(1,tiempoEstablecido.getRuta().getCodigo());
			stm.setInt(2,tiempoEstablecido.getPunto().getCodigo());
			stm.setInt(3,tiempoEstablecido.getTiempoEstablecido());
			stm.setInt(4,tiempoEstablecido.getOrden());
			stm.setBoolean(5,tiempoEstablecido.isVigencia());
		    stm.execute();
			
			Conexion.cerrarConexion();
			
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		
	}
	public void actualizarTiempoRuta(TiempoEstablecido tiempoEstablecido) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_aumentarTiempoRuta(?,?)}");
			stm.setInt(1,tiempoEstablecido.getRuta().getCodigo());
			stm.setInt(2,tiempoEstablecido.getTiempoEstablecido());
			stm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		
	}
	
	public void EliminarTiempoEstablecido(TiempoEstablecido tiempoEstablecido) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_eTiempoEstablecido(?,?)}");
			stm.setInt(1,tiempoEstablecido.getCodigo());
			stm.setInt(2,tiempoEstablecido.getRuta().getCodigo());
		    stm.execute();	
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public List<TiempoEstablecido> listarTiemposRuta(int codigoRuta) throws Exception{
		List<TiempoEstablecido> listaTiempos = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liTiemposVigentesRuta(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoRuta);
			rs = cstm.executeQuery();
			while(rs.next()) {
				TiempoEstablecido tiempo = new TiempoEstablecido();
				PuntoControl punto = new PuntoControl();
				Ruta ruta = new Ruta();
				punto.setCodigo(rs.getInt("p_codigoPuntoControl"));
				punto.setDireccion(rs.getString("p_direccion"));
				punto.obtenerPunto(rs.getString("p_punto"));
				ruta.setCodigo(rs.getInt("p_codigoRuta"));
				ruta.setLetra(rs.getString("p_letra"));
				tiempo.setPunto(punto);
				tiempo.setRuta(ruta);
				tiempo.setCodigo(rs.getInt("p_codigo"));
				tiempo.setTiempoEstablecido(rs.getInt("p_tiempoEstablecido"));
				tiempo.setOrden(rs.getInt("p_orden"));
				tiempo.setVigencia(rs.getBoolean("p_vigencia"));
				listaTiempos.add(tiempo);
			}
			return listaTiempos;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void modificarTiempoEstablecido(TiempoEstablecido tiempoEstablecido)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_aTiempoEstablecido(?,?,?,?)}");
			stm.setInt(1,tiempoEstablecido.getCodigo());
			stm.setInt(2,tiempoEstablecido.getRuta().getCodigo());
			stm.setInt(3,tiempoEstablecido.getTiempoEstablecido());
			stm.setInt(4,tiempoEstablecido.getOrden());
			stm.execute();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
