package com.chasquiSA.microInformacion.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.GPSUnidadDAO;
import com.chasquiSA.microInformacion.Dominio.GPS;

@RestController
@RequestMapping("/gpsunidad")
public class GPSUnidad {
	@Autowired
	GPSUnidadDAO dao;
	@GetMapping(value = "/listarGPS/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GPS> listarGPSUnidad(@PathVariable("id") Integer codigoUnidad)throws Exception{
		try {
			GPS gps = new GPS();
			gps = dao.obtenerGPS(codigoUnidad);
			return new ResponseEntity<GPS>(gps,HttpStatus.OK);
		}catch(Exception e){
			throw e;
		}
	}
}
