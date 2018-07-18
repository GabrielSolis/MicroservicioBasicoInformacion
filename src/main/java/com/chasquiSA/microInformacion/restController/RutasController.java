package com.chasquiSA.microInformacion.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.RutaDAO;
import com.chasquiSA.microInformacion.Dominio.CalleRuta;
import com.chasquiSA.microInformacion.Dominio.Ruta;

@RestController
@RequestMapping("/rutas")
public class RutasController {
	@RequestMapping(value="/{codigoRuta}",method= RequestMethod.POST)
	public int registrarCalleRuta(@PathVariable int codigoRuta,@RequestBody Ruta ruta)throws Exception {
		RutaDAO dao = new RutaDAO(); 
		int codigo;
		try {
			ruta.setCodigo(codigoRuta);
			codigo=dao.registrarCalleRuta(ruta);
			return codigo;
		}catch(Exception e) {
			throw e;
		}
	}

	
	@RequestMapping(value="/",method= RequestMethod.POST)
	public ResponseEntity<?> registrarRuta(@RequestBody Ruta ruta)throws Exception {
		RutaDAO dao = new RutaDAO(); 
		try {

			if(!dao.verificarRuta(ruta.getLetra())) {
				dao.registrarRuta(ruta);
				return new ResponseEntity<>("Ruta registrada exitosamente",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Ruta ya registrada",HttpStatus.CONFLICT);
			}
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public ResponseEntity<?> listarRutas() throws Exception{
		List<Ruta> listaRutas;
		RutaDAO dao = new RutaDAO();
		try {
			listaRutas = dao.listarRutasVigentes();
			return new ResponseEntity<>(listaRutas,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@RequestMapping(value = "/{estado}" , method = RequestMethod.GET)
	public ResponseEntity<?> listarRutasEstado(@PathVariable String estado)throws Exception{
		List<Ruta> listaRutas;
		RutaDAO dao = new RutaDAO();
		try {
			listaRutas = dao.listarRutasEstado(estado);
			return new ResponseEntity<>(listaRutas,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@RequestMapping(value = "/" , method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarRuta(@RequestBody Ruta ruta)throws Exception{
		try {
			RutaDAO dao = new RutaDAO();
			//if(!dao.verificarRuta(ruta.getLetra())) {
				dao.modificarRuta(ruta);
			//	return new ResponseEntity<>("Ruta modificada exitosamente",HttpStatus.OK);
			//}else {
				return new ResponseEntity<>("Ruta modicada exitosamente" , HttpStatus.OK);
			//}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/{codigoCalleRuta}",method=RequestMethod.PUT)
	public ResponseEntity<?> actualizarCalle(@PathVariable int codigoCalleRuta, @RequestBody CalleRuta calle) throws Exception{
		try {
			calle.setCodigo(codigoCalleRuta);
			RutaDAO dao = new RutaDAO();
			dao.modifiarCalleRuta(calle);
			return new ResponseEntity<>("Calle modificada exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	@RequestMapping(value="/{codigoCalleRuta}",method=RequestMethod.DELETE)
	public ResponseEntity<?> eliminarCalle(@PathVariable int codigoCalleRuta) throws Exception{
		try {
			RutaDAO dao = new RutaDAO();
			dao.eliminarCalleRuta(codigoCalleRuta);;
			return new ResponseEntity<>("Ruta dada de baja",HttpStatus.OK); //decia punto
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/",method=RequestMethod.DELETE)
	public ResponseEntity<?> eliminarRuta(@RequestBody Ruta ruta) throws Exception{
		try {
			RutaDAO dao = new RutaDAO();
			dao.eliminarRuta(ruta);;
			return new ResponseEntity<>("Ruta dada de baja",HttpStatus.OK); //decia punto
		}catch(Exception e) {
			throw e;
		}
	}
}
