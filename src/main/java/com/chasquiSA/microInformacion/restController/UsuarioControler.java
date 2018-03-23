package com.chasquiSA.microInformacion.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.UsuarioDAO;
import com.chasquiSA.microInformacion.Dominio.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {
	@PostMapping("/")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario)throws Exception{
		try {
			String nombreUsuario;
			UsuarioDAO dao  = new UsuarioDAO();
			nombreUsuario = dao.registrarUsuario(usuario);
			return new ResponseEntity<>(nombreUsuario,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modificarUsuario(@RequestBody Usuario usuario) throws Exception{
		boolean bandera;
		try {
			UsuarioDAO dao = new UsuarioDAO();
			bandera = dao.modificarUsuario(usuario);
			System.out.println(bandera);
			if(bandera) {
				return new ResponseEntity<>("Usuario modificado exitosamente",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Clave actual errada",HttpStatus.OK);
			}
			
		}catch(Exception e) {
			throw e;
		}
	}
	@GetMapping("/")
	public ResponseEntity<?> obtenerUsuario(@RequestParam int codigo) throws Exception{
		try {
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = new Usuario();
			usuario = dao.getUsuario(codigo);
			return new ResponseEntity<>(usuario,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) throws Exception{
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioL = null;
		try {
			usuarioL = dao.login(usuario);
			if(usuarioL != null) {
				return new ResponseEntity<>(usuarioL.getCodigo(),HttpStatus.OK);
			}
			return new ResponseEntity<>("Credenciales incorrectas",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@GetMapping("/{estado}")
	public ResponseEntity<?> listarUsuariosEstado(@PathVariable("estado") String estado)throws Exception{
		
		List<Usuario> listaUsuarios;
		UsuarioDAO dao = new UsuarioDAO();
		try {
			listaUsuarios = dao.listarUsuariosEstado(estado);
			return new ResponseEntity<>(listaUsuarios,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> cambiarEstadoUsuario(@RequestBody Usuario usuario)throws Exception{
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.cambiarVigencia(usuario);
			return new ResponseEntity<>("El usuario paso a estado inactivo",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
}
