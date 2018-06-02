package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.chasquiSA.microInformacion.Dominio.GPS;

@Repository
public class GPSUnidadDAO {
	public GPS obtenerGPS(int codigoUnidad)throws Exception{
		GPS gps = new GPS();
		try {
			Connection conexion = Conexion.getConexion();
			
			CallableStatement cstm = conexion.prepareCall("{call pr_iGPS(?)}");
			ResultSet rs;
			cstm.setInt(1,codigoUnidad);
			rs = cstm.executeQuery();
			rs.next();
			gps.setCodigo(rs.getInt("codigo"));
			gps.setCodigoReferencia(rs.getString("codigoReferencia"));
			return gps;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
}
