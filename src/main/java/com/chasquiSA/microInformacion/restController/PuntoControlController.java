package com.chasquiSA.microInformacion.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.PuntoControlDAO;
import com.chasquiSA.microInformacion.Dominio.PuntoControl;
@RestController
@RequestMapping("/puntos")
public class PuntoControlController {

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> registrarPunto(@RequestBody PuntoControl punto) throws Exception {
		try {
			PuntoControlDAO dao = new PuntoControlDAO();
			//System.out.println(punto.getDireccion() + " " + punto.getLatitud() + " " + punto.getLongitud());
			punto.setCodigo(dao.registrarPunto(punto));
			return new ResponseEntity<>(punto,HttpStatus.OK);
		}catch(Exception e) {
			//return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw e;
		}
		
	}
//	@RequestMapping(value="/",method=RequestMethod.GET)
//	public ResponseEntity<?> listarPuntos()throws Exception{
//		List<PuntoControl> listaPuntos = new ArrayList<>();
//		PuntoControlDAO dao = new PuntoControlDAO();
//		try {
//			listaPuntos = dao.listarPuntos();
//			return new ResponseEntity<>(listaPuntos,HttpStatus.OK);
//		}catch(Exception e) {
//			throw e;
//		}
//	}
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public ResponseEntity<?> modificarPunto(@RequestBody PuntoControl punto) throws Exception{
		try {
			PuntoControlDAO dao = new PuntoControlDAO();
			dao.modificarPunto(punto);
			return new ResponseEntity<>("Punto Modificado exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@RequestMapping(value="/",method=RequestMethod.DELETE)
	public ResponseEntity<?> actualizarPunto(@RequestBody PuntoControl punto) throws Exception{
		try {
			PuntoControlDAO dao = new PuntoControlDAO();
			dao.cambiarEstado(punto);
			return new ResponseEntity<>("Punto dado de baja",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<?> listarPuntosEstado(@RequestParam String estado)throws Exception{
		List<PuntoControl> listaPuntos = new ArrayList<>();
		PuntoControlDAO dao = new PuntoControlDAO();
		try {
			listaPuntos = dao.listarPuntosEstado(estado);
			return new ResponseEntity<>(listaPuntos,HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	
}
