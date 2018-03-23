package com.chasquiSA.microInformacion.restController;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.TiempoEstablecidoDAO;
import com.chasquiSA.microInformacion.Dominio.TiempoEstablecido;

@RestController
@RequestMapping("/tiempoEstablecido")
public class TiempoEstablecidoController {
	@PostMapping("/")
	public ResponseEntity<?> nuevoTiempoEstablecido(@RequestBody TiempoEstablecido tiempoEstablecido)throws Exception{
		TiempoEstablecidoDAO dao = new TiempoEstablecidoDAO();
		
		try {
			dao.registrarTiempoEstabelcido(tiempoEstablecido);
			dao.actualizarTiempoRuta(tiempoEstablecido);
			return new ResponseEntity<>("Tiempo registrado exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}

	@GetMapping("/{codigoRuta}")
	public ResponseEntity<List<TiempoEstablecido>> listarTiemposVigentesRuta(@PathVariable int codigoRuta)throws Exception{
		TiempoEstablecidoDAO dao = new TiempoEstablecidoDAO();
		List<TiempoEstablecido> listaTiempos = new ArrayList<>();
		try {
			listaTiempos = dao.listarTiemposRuta(codigoRuta);
			return new ResponseEntity<List<TiempoEstablecido>>(listaTiempos,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> actualizarTiempoEstablecido(@RequestBody TiempoEstablecido tiempoEstablecido) throws Exception{
		TiempoEstablecidoDAO dao = new TiempoEstablecidoDAO();
		try {
			dao.modificarTiempoEstablecido(tiempoEstablecido);
			dao.actualizarTiempoRuta(tiempoEstablecido);
			return new ResponseEntity<>("Tiempo modificado exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> eliminarTiempoEstablecido(@RequestBody TiempoEstablecido tiempoEstablecido) throws Exception{
		TiempoEstablecidoDAO dao = new TiempoEstablecidoDAO();
		try {
			dao.EliminarTiempoEstablecido(tiempoEstablecido);
			return new ResponseEntity<>("Tiempo Eliminado exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
}
