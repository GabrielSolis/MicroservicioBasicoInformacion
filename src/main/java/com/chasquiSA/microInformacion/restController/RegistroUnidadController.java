package com.chasquiSA.microInformacion.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.UnidadDAO;
import com.chasquiSA.microInformacion.Dominio.RegistroUnidad;

@RestController
@RequestMapping("/registroUnidades")
public class RegistroUnidadController {
	@RequestMapping(value="/{id}",method=RequestMethod.GET )
	public ResponseEntity<?> extraerRegistroUnidadSocio(@RequestParam String estado,@PathVariable int id){
		List<RegistroUnidad> rUnidades = new ArrayList<>();
		UnidadDAO dao = new UnidadDAO();
		try {
			
			rUnidades = dao.listarUnidadesSocio(estado.toUpperCase(),id);
			
			if(rUnidades != null){
				return new ResponseEntity<>(rUnidades,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No hay unidades", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			//System.out.println(e.getMessage());;
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}	
	
	@GetMapping(value = "/listar/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RegistroUnidad>> listarUnidades() throws Exception{
		List<RegistroUnidad> rUnidades = new ArrayList<>();
		UnidadDAO dao = new UnidadDAO();
		try {
			rUnidades = dao.listarUnidadesActivas();
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<List<RegistroUnidad>>(rUnidades,HttpStatus.OK);
	}
}