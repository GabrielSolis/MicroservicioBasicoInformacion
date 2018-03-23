package com.chasquiSA.microInformacion.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.UnidadDAO;
import com.chasquiSA.microInformacion.Dominio.RegistroUnidad;
import com.chasquiSA.microInformacion.Dominio.socioPlaca;


@RestController
@RequestMapping("/unidades")
public class UnidadController {
	@RequestMapping(value = "/" , method = RequestMethod.POST)
	public ResponseEntity<?> nuevaUnidad(@RequestBody RegistroUnidad rUnidad){
		UnidadDAO dao = new UnidadDAO();
		try {
			
			if(!dao.verificarPlaca(rUnidad.getUnidad().getPlaca())) {
				//socio.asignarFechaRegistro();
				rUnidad.getUnidad().asignarVigencia();
				//unidad.asignarFechaRegistro();
				dao.registrarUnidad(rUnidad);
				if(rUnidad.getUnidad().getEstado().equals("A") || rUnidad.getUnidad().getEstado().equals("I")) {
					rUnidad.getSocio().aumentarNumeroAcciones();
					dao.modificarNumeroAccionesSocio(rUnidad);
				}
				return new ResponseEntity<>("Creado",HttpStatus.OK);
			}
			return new ResponseEntity<>("Unidad o Vehiculo ya registrado",HttpStatus.CONFLICT);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarUnidades(@RequestParam String estado,@RequestParam String apellido																			){
		List<socioPlaca> socioPlaca = new ArrayList<>();
		UnidadDAO dao = new UnidadDAO();
		try {
			
			socioPlaca=dao.listar(estado.toUpperCase(),apellido.toLowerCase().replaceAll(" ",""));
			if(socioPlaca != null) {
				return new ResponseEntity<>(socioPlaca,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No hay unidades", HttpStatus.NOT_FOUND);
				
			}
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
//	@CrossOrigin(origins = "http://localhost:8080")
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<?> listarUnidades(@RequestParam String estado,@RequestParam String apellido																			){
//		List<RegistroUnidad> rUnidades = new ArrayList<>();
//		UnidadDAO dao = new UnidadDAO();
//		try {
//			
//			rUnidades=dao.listar(estado.toUpperCase(),apellido.toLowerCase().replaceAll(" ",""));
//			if(rUnidades != null) {
//				return new ResponseEntity<>(rUnidades,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<>("No hay unidades", HttpStatus.NOT_FOUND);
//				
//			}
//		}catch(Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}

	@RequestMapping(value = "/",method=RequestMethod.DELETE )
	public ResponseEntity<?> cambiarVigenciaUnidad(@RequestBody RegistroUnidad rUnidad)throws Exception{
		UnidadDAO dao = new UnidadDAO();
		try {
			//socio.asignarFechaRetiro();
			
			if(rUnidad.getUnidad().getEstado().equals("A") || rUnidad.getUnidad().getEstado().equals("I")) {
				rUnidad.getSocio().desminuirNumeroAcciones();
				//System.out.println(rUnidad.getSocio().getCodigo() + " - " + rUnidad.getSocio().getNumeroAcciones());
				dao.modificarNumeroAccionesSocio(rUnidad);
				
			}
			if(rUnidad.getUnidad().getEstado().equals("Y")) {
				
			}
			rUnidad.asignarFechaRetiro();
			dao.modificarVigencia(rUnidad);
			return new ResponseEntity<>("Eliminado Exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET )
	public ResponseEntity<?> extraerRegistroUnidad(@PathVariable int id){
		RegistroUnidad rUnidad = new RegistroUnidad();
		UnidadDAO dao = new UnidadDAO();
		try {
			
			rUnidad = dao.getRegistroUnidad(id);
			
			if(rUnidad != null){
				return new ResponseEntity<>(rUnidad,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No existe El Registro Unidad",HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			//System.out.println(e.getMessage());;
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	} 
	
	
	@RequestMapping(value="/",method=RequestMethod.PUT )
	public ResponseEntity<?> modificandoRegistroUnidad(@RequestBody RegistroUnidad rUnidad, @RequestParam String estado){
		UnidadDAO dao = new UnidadDAO();
		try {
				//rUnidad.getSocio().aumentarNumeroAcciones();
				//dao.modificarNumeroAccionesSocio(rUnidad);
			//System.out.println(estado);
			//System.out.println(rUnidad.getSocio().getNumeroAcciones());
				if(estado.equals("A") && rUnidad.getUnidad().getEstado().equals("Y")) {
					rUnidad.getSocio().desminuirNumeroAcciones();
					dao.modificarNumeroAccionesSocio(rUnidad);
				}
				if (estado.equals("I") && rUnidad.getUnidad().getEstado().equals("Y")) {
					rUnidad.getSocio().desminuirNumeroAcciones();
					dao.modificarNumeroAccionesSocio(rUnidad);
				}
				if (estado.equals("Y") && rUnidad.getUnidad().getEstado().equals("A")) {
					rUnidad.getSocio().aumentarNumeroAcciones();
					dao.modificarNumeroAccionesSocio(rUnidad);
				}
				if (estado.equals("Y") && rUnidad.getUnidad().getEstado().equals("I")) {
					rUnidad.getSocio().aumentarNumeroAcciones();
					dao.modificarNumeroAccionesSocio(rUnidad);
				}
				dao.modificarRegistroUnidad(rUnidad);
				return new ResponseEntity<>("Modificiación exitosa",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
