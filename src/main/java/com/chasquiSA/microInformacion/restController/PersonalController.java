package com.chasquiSA.microInformacion.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.PersonalDAO;

import com.chasquiSA.microInformacion.Dominio.Personal;

@RestController
@RequestMapping("/personal")
//@CrossOrigin(origins = "http://localhost:8080")
public class PersonalController {
	@PostMapping("/")
	public ResponseEntity<?> insertarPersonal(@RequestBody Personal personal) throws Exception{
		PersonalDAO dao = new PersonalDAO();
	
		try {
			if(!dao.verificarDNI(personal.getDni())) {
				//socio.asignarFechaRegistro();
				dao.registrarPersonal(personal);
				
				return new ResponseEntity<>("!Personal registrado exitosamente¡",HttpStatus.OK);
			}
			return new ResponseEntity<>("Dni ya registrado",HttpStatus.CONFLICT);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> listarPersonal(@RequestParam String estado,@RequestParam String apellido)throws Exception{
		List<Personal> personal = new ArrayList<>();
		PersonalDAO dao = new PersonalDAO();
		try {
			
			personal = dao.listar(estado.toUpperCase(),apellido.toLowerCase().replaceAll(" ",""));
			
			if(personal != null){
				return new ResponseEntity<>(personal,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No hay personal",HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET )
	public ResponseEntity<?> mostrarPersonal(@PathVariable int id) throws Exception{
		Personal personal = new Personal();
		PersonalDAO dao = new PersonalDAO();
		try {
			
			personal = dao.getPersonal(id);
			
			if(personal != null){
				return new ResponseEntity<>(personal,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No existe personal",HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			throw e;
		}
	} 
	
	@RequestMapping(value="/",method=RequestMethod.PUT )
	public ResponseEntity<?> modificarPersonal(@RequestBody Personal personal)throws Exception{
		PersonalDAO dao = new PersonalDAO();
		try {

				dao.modificarPersonal(personal);
				return new ResponseEntity<>("Personal modificado exitosamente",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	} 
	
	@RequestMapping(value="/",method=RequestMethod.DELETE )
	public ResponseEntity<?> cambiarEstadoSocio(@RequestBody Personal personal)throws Exception{
		PersonalDAO dao = new PersonalDAO();
		try {
			personal.asignarFechaRetiro();
			dao.modificarEstado(personal);
			return new ResponseEntity<>("Creado",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@GetMapping(value="/listadoPersonal/reporte/{estado}/{apellido}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> reporteListadoPersonal(@PathVariable("estado")String estado,@PathVariable("apellido")String apellido)throws Exception{
		byte[] data = null;
		PersonalDAO dao = new PersonalDAO();
		try {
			data = dao.listadoPersonalReporte(estado, apellido);
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
