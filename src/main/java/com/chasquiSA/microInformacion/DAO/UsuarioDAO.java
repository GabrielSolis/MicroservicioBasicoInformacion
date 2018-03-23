package com.chasquiSA.microInformacion.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.chasquiSA.microInformacion.Dominio.Personal;
import com.chasquiSA.microInformacion.Dominio.Usuario;

public class UsuarioDAO {
	public String registrarUsuario(Usuario usuario)throws Exception {
		
		try {
			Connection conexion = Conexion.getConexion();
			
			CallableStatement cstm = conexion.prepareCall("{call pr_iUsuario(?,?)}");
			ResultSet rs;
			cstm.setInt(1,usuario.getPersonal().getCodigo());
			cstm.setBoolean(2,usuario.isVigencia());
			rs = cstm.executeQuery();
			rs.next();
			return rs.getString(1);
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public boolean verificarContrasenia(Usuario usuario)throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			ResultSet rs;
			CallableStatement cstm = conexion.prepareCall("{call pr_verificarContrasenia(?,?)}");
			cstm.setInt(1,usuario.getCodigo());
			cstm.setString(2,usuario.getClaveAntigua());
			rs = cstm.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public boolean modificarUsuario(Usuario usuario) throws Exception{
		boolean bandera;
		try {
			if(verificarContrasenia(usuario)) {
				Connection conexion = Conexion.getConexion();
				CallableStatement stm = conexion.prepareCall("{call pr_aUsuario(?,?)}");
				stm.setInt(1,usuario.getCodigo());
				stm.setString(2,usuario.getClave());
				bandera = stm.execute();
				return bandera;
			}else {
				return false;
			}
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public List<Usuario> listarUsuariosEstado(String estado)throws Exception{
		List<Usuario> lista = new ArrayList<>();
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement cstm = conexion.prepareCall("{call pr_liUsuario(?)}");
			ResultSet rs;
		
			cstm.setString(1,estado);
			rs=cstm.executeQuery();
			while(rs.next()) {
				
				Usuario  usuario = new Usuario();
				Personal personal = new Personal();
				personal.setCodigo(rs.getInt("p_codigoPersonal"));
				usuario.setCodigo(rs.getInt("p_codigo"));
				usuario.setUsuario(rs.getString("p_usuario"));
				personal.setNombres(rs.getString("p_nombres"));
				personal.setCargo(rs.getString("p_cargo"));
				personal.setApellidoPaterno(rs.getString("p_apellidoPaterno"));
				personal.setApellidoMaterno(rs.getString("p_materno"));
				usuario.setPersonal(personal);
				lista.add(usuario);
			}
			return lista;
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public Usuario getUsuario(int codigo) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			Usuario usuario = new Usuario();
			CallableStatement stm = conexion.prepareCall("{call pr_lUsuario(?)}");
			stm.setInt(1,codigo);
			ResultSet rs;
			rs = stm.executeQuery();
			rs.next();
			usuario.setCodigo(rs.getInt("p_codigo"));
			usuario.setClave(rs.getString("p_contrasenia"));
			usuario.setUsuario(rs.getString("p_usuario"));
			usuario.setIndicador(rs.getInt("p_indicador"));
			return usuario;
		}catch(Exception e){
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	
	public void cambiarVigencia(Usuario usuario) throws Exception{
		try {
			Connection conexion = Conexion.getConexion();
			CallableStatement stm = conexion.prepareCall("{call pr_eUsuario(?,?)}");
			stm.setInt(1, usuario.getCodigo());
			stm.setBoolean(2,usuario.isVigencia());
			stm.execute();
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
	}
	public Usuario login(Usuario usuarioLogin) throws Exception{
		Usuario usuario = null;
		try {
			Connection conexion = Conexion.getConexion();
			
			CallableStatement cstm = conexion.prepareCall("{call pr_login(?,?)}");
			ResultSet rs;
			cstm.setString(1,usuarioLogin.getUsuario());
			cstm.setString(2, usuarioLogin.getClave());
			rs = cstm.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setUsuario(rs.getString("p_usuario"));
				usuario.setCargo(rs.getString("p_cargo"));
				usuario.setCodigo(rs.getInt("p_codigo"));
				usuario.setIndicador(rs.getInt("p_indicador"));
				usuario.setVigencia(rs.getBoolean("p_vigencia"));
			}
		}catch(Exception e) {
			throw e;
		}finally {
			Conexion.cerrarConexion();
		}
		return usuario;
	}
}
