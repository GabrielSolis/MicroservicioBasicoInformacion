package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chasquiSA.microInformacion.Dominio.CalleRuta;
import com.chasquiSA.microInformacion.Dominio.Ruta;

public class RutaDAO {
	
	public void registrarRuta(Ruta ruta)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			
			CallableStatement stm = conexion.prepareCall("{call pr_iRuta(?,?,?,?)}");
			stm.setString(1,ruta.getLetra());
			stm.setString(2,ruta.getEstado());
			stm.setInt(3,ruta.getTiempo());
			stm.setBoolean(4,ruta.isVigencia());
			 stm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		
	}
	public boolean verificarRuta(String letra) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_ComprobarRuta(?)}");
			
			ResultSet rs;
			cstm.setString(1,letra);
			rs = cstm.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch(Exception e) {
			throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	public List<Ruta> listarRutasEstado(String estado)throws Exception{
		List<Ruta> lista = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liRutasEstado(?)}");
			CallableStatement cstm2 = conexion.prepareCall("{call pr_liCallesRutaVigentes(?)}");
			ResultSet rs;
			ResultSet rs2;
			cstm.setString(1,estado);
			rs=cstm.executeQuery();
			while(rs.next()) {
				List<CalleRuta> listaCalles = new ArrayList<>();	
				Ruta ruta = new Ruta();
				cstm2.setInt(1,rs.getInt("p_codigo"));
				rs2 = cstm2.executeQuery();
				while(rs2.next()) {
					CalleRuta calle = new CalleRuta();
					calle.setCodigo(rs2.getInt("p_codigo"));
					calle.setDireccion(rs2.getString("p_direccion"));
					calle.obtenerPunto(rs2.getString("p_punto"));
					calle.setNumeroOrden(rs2.getInt("p_numeroOrden"));
					calle.setVigencia(rs2.getBoolean("p_vigencia"));
					listaCalles.add(calle);
				}
				ruta.setCalles(listaCalles);
				ruta.setCodigo(rs.getInt("p_codigo"));
				ruta.setLetra(rs.getString("p_letra"));
				ruta.setEstado(rs.getString("p_estado"));
				ruta.setTiempo(rs.getInt("p_tiempo"));
				ruta.setVigencia(rs.getBoolean("p_vigencia"));
				lista.add(ruta);
			}
			return lista;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public List<Ruta> listarRutasVigentes()throws Exception{
		List<Ruta> lista = new ArrayList<>();
		ResultSet rs;
		ResultSet rs2;
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liRutasVigentes()}");
			CallableStatement cstm2 = conexion.prepareCall("{call pr_liCallesRutaVigentes(?)}");
			rs = cstm.executeQuery();
			while(rs.next()) {
				List<CalleRuta> listaCalles = new ArrayList<>();	
				Ruta ruta = new Ruta();
				//cstm2.setInt(1,rs.getInt("p_codigo"));
				cstm2.setInt(1,4);
				Logger log = Logger.getLogger("Logger de Ejemplo");
				log.info(rs.getInt("p_codigo"));
				rs2 = cstm2.executeQuery();
				
					while(rs2.next()) {		
						
						log.info(rs2.getString("p_punto"));
						CalleRuta calle = new CalleRuta();
						calle.setCodigo(rs2.getInt("p_codigo"));
						calle.setDireccion(rs2.getString("p_direccion"));
						calle.obtenerPunto(rs2.getString("p_punto"));
						calle.setNumeroOrden(rs2.getInt("p_numeroOrden"));
						calle.setVigencia(rs2.getBoolean("p_vigencia"));
						listaCalles.add(calle);
				}
				
				ruta.setCalles(listaCalles);
				ruta.setCodigo(rs.getInt("p_codigo"));
				ruta.setLetra(rs.getString("p_letra"));
				ruta.setEstado(rs.getString("p_estado"));
				
				ruta.setVigencia(rs.getBoolean("p_vigencia"));
				lista.add(ruta);
				//System.out.println(ruta.getCalles().size());
			}
			Conexion.cerrarConexion();
			return lista;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public int registrarCalleRuta(Ruta ruta)throws Exception{
		
		try {
			Connection conexion = Conexion.getConexion();
			ResultSet rs;
			int indice;
			CallableStatement stm = conexion.prepareCall("{call pr_iCalleRuta(?,?,?,?,?,?)}");
			indice = ruta.getCalles().size() -1;
			stm.setInt(1,ruta.getCodigo());
			stm.setString(2,ruta.getCalles().get(indice).getDireccion());
			stm.setDouble(3,ruta.getCalles().get(indice).getLatitud());
			stm.setDouble(4,ruta.getCalles().get(indice).getLongitud());
			stm.setInt(5,ruta.getCalles().get(indice).getNumeroOrden());
			stm.setBoolean(6,ruta.getCalles().get(indice).isVigencia());
			rs=stm.executeQuery();
			rs.next();
			Conexion.cerrarConexion();
			return rs.getInt(1);
		}catch(Exception e) {
				throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		
	}
	
	
	public void modificarRuta(Ruta ruta)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_aRuta(?,?,?)}");
			 stm.setInt(1,ruta.getCodigo());
			 stm.setString(2,ruta.getLetra());
			 stm.setString(3,ruta.getEstado());
			 stm.execute();
			 Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void modifiarCalleRuta(CalleRuta calle)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_aCalleRuta(?,?,?)}");
			 stm.setInt(1,calle.getCodigo());
			 stm.setString(2,calle.getDireccion());
			 stm.setInt(3,calle.getNumeroOrden());
			 stm.execute();
			 Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public void eliminarCalleRuta(int codigoCalleRuta)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm =  conexion.prepareCall("{call pr_eCalleRuta(?)}");
			stm.setInt(1,codigoCalleRuta);
			stm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void eliminarRuta(Ruta ruta)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm =  conexion.prepareCall("{call pr_eRuta(?)}");
			stm.setInt(1,ruta.getCodigo());
			stm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	
}
