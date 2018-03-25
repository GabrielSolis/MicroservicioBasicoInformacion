package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.chasquiSA.microInformacion.Dominio.Socio;

public class SocioDAO {
	
//	@Autowired
//	private DataSource dataSource;
	public void registrarSocio(Socio socio)throws Exception{
		try {
			//Connection conexion = dataSource.getConnection();
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_isocio(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstm.setString(1,socio.getNombres());
			cstm.setString(2,socio.getApellidoPaterno());
			cstm.setString(3,socio.getApellidoMaterno());
			cstm.setString(4,socio.getCorreo());
			cstm.setString(5,socio.getDireccion());
			cstm.setString(8,socio.getFechaNacimiento());
			cstm.setString(7,socio.getSexo());
			cstm.setString(6,socio.getDni());
			cstm.setString(9,socio.getCelular());
			cstm.setString(10,socio.getEstadoCivil());
			cstm.setString(11,socio.getFechaRegistro());
			cstm.setString(12,socio.getNombresApellidosPariente());
			cstm.setString(13,socio.getTelefonoPariente());
			cstm.setString(14,socio.getEstado());
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}
	}
	public List<Socio> listar(String estado, String apellido)throws Exception{
		List<Socio> socios = new ArrayList<>();
	
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liSociosEstadoOrApellido(?,?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
			cstm.setString(1,estado);
			cstm.setString(2,apellido);
			rs = cstm.executeQuery();
			while(rs.next()) {
			
				Socio socio = new Socio();
				socio.setNombres(rs.getString("nombres"));
				socio.setApellidoPaterno(rs.getString("apellidopaterno"));
				socio.setApellidoMaterno(rs.getString("apellidomaterno"));
				socio.setNumeroAcciones(rs.getInt("numeroacciones"));
				socio.setDni(rs.getString("dni"));
				socio.setEstado(rs.getString("estado"));
				socio.setCodigo(rs.getInt("codigoPersona"));
			
				socios.add(socio);
			}
			Conexion.cerrarConexion();
			return socios;
		}catch(Exception e) {
			throw e;
		}finally{
			socios = null;
			
		}

	}
	public void modificarSocio(Socio socio)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_asocio(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstm.setInt(1,socio.getCodigo());;
			cstm.setString(2,socio.getNombres());
			cstm.setString(3,socio.getApellidoPaterno());
			cstm.setString(4,socio.getApellidoMaterno());
			cstm.setString(5,socio.getCorreo());
			cstm.setString(6,socio.getDireccion());
			cstm.setString(7,socio.getSexo());
			cstm.setString(8,socio.getFechaNacimiento());
			cstm.setString(9,socio.getCelular());
			cstm.setString(10,socio.getEstadoCivil());
			cstm.setString(11,socio.getFechaRegistro());
			cstm.setString(12,socio.getNombresApellidosPariente());
			cstm.setString(13,socio.getTelefonoPariente());
			
		
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
		}finally{
			Conexion.cerrarConexion();
		}
	}
	
	public void modificarEstado(Socio socio)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_aSocioEstado(?,?,?)}");
			cstm.setInt(1,socio.getCodigo());;
			cstm.setString(2,socio.getEstado());
			cstm.setString(3,socio.getFechaRetiro());
			System.out.println("Desde el DAO"+socio.getFechaRetiro());
			//System.out.println(cstm.getParameterMetaData().getParameterClassName(6) + " " + socio.getNombresApellidosPariente() + " "+ cstm.getParameterMetaData().getParameterClassName(13) );
			cstm.execute();
			Conexion.cerrarConexion();
		}catch(Exception e) {
				throw e;
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
		}
	}
	public Socio getSocio(int id) throws Exception {
		Socio socio = new Socio();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_lSocio(?)}");
			//Statement sts = conexion.createStatement();
			ResultSet rs;
			cstm.setInt(1,id);
			rs = cstm.executeQuery();
			
			while(rs.next()) {
				socio.setNombres(rs.getString("nombres"));
				socio.setApellidoPaterno(rs.getString("apellidopaterno"));
				socio.setApellidoMaterno(rs.getString("apellidomaterno"));
				socio.setSexo(rs.getString("sexo"));
				socio.setNumeroAcciones(rs.getInt("numeroacciones"));
				socio.setDni(rs.getString("dni"));
				socio.setCelular(rs.getString("celular"));
				socio.setEstado(rs.getString("estado"));
				socio.setCodigo(rs.getInt("codigoPersona"));
				socio.setCorreo(rs.getString("correo"));
				socio.setDireccion(rs.getString("direccion"));
				socio.setFechaNacimiento(rs.getString("fechanacimiento"));
				socio.setFechaRegistro(rs.getString("fecharegistro"));	
				socio.setFechaRetiro(rs.getString("fecharetiro"));
				socio.setNombresApellidosPariente(rs.getString("nombrespariente"));
				socio.setEstadoCivil(rs.getString("estadocivil"));
				socio.setTelefonoPariente(rs.getString("telefonopariente"));
				socio.setVigencia(rs.getBoolean("vigencia"));
			}
			Conexion.cerrarConexion();
			return socio;
		}catch(Exception e) {
			throw e;
		}finally{
			socio = null;
			
		}
	}
}
