package com.chasquiSA.microInformacion.restController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chasquiSA.microInformacion.DAO.SocioDAO;
import com.chasquiSA.microInformacion.Dominio.Socio;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/socios")
public class SocioController {
	@RequestMapping(value = "/" , method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> nuevoSocio(@RequestBody Socio socio){
		SocioDAO dao = new SocioDAO();
		try {
			if(!dao.verificarDNI(socio.getDni())) {
				//socio.asignarFechaRegistro();
				socio.asignarEstado();
				dao.registrarSocio(socio);
				return new ResponseEntity<>("Creado",HttpStatus.OK);
			}
			return new ResponseEntity<>("Dni ya registrado",HttpStatus.CONFLICT);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/" , method= RequestMethod.GET)
	public ResponseEntity<?> listarSocios(@RequestParam String estado,@RequestParam String apellido){
		List<Socio> socios = new ArrayList<>();
		SocioDAO dao = new SocioDAO();
		try {
			
			socios = dao.listar(estado.toUpperCase(),apellido.toLowerCase().replaceAll(" ",""));
			
			if(socios != null){
				return new ResponseEntity<>(socios,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No hay socios",HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET )
	public ResponseEntity<?> mostrarSocio(@PathVariable int id){
		Socio socio = new Socio();
		SocioDAO dao = new SocioDAO();
		try {
			
			socio = dao.getSocio(id);
			
			if(socio != null){
				return new ResponseEntity<>(socio,HttpStatus.OK);
			}else {
				return new ResponseEntity<>("No existe socio",HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			//System.out.println(e.getMessage());;
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	} 
	@RequestMapping(value="/",method=RequestMethod.PUT )
	public ResponseEntity<?> modificarSocio(@RequestBody Socio socio){
		SocioDAO dao = new SocioDAO();
		try {
//				System.out.println(socio.getApellidoMaterno() + " " + socio.getApellidoMaterno() + " " + socio.getCelular()+ " " + socio.getCodigo() + " " + socio.getCorreo() 
//				+ " " + socio.getEstadoCivil() + " " + socio.getDireccion() + " " + socio.getFechaRegistro() + " " +socio.getNombresApellidosPariente() + " " + socio.getTelefonoPariente());
				dao.modificarSocio(socio);
				return new ResponseEntity<>("Creado",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	} 
	@RequestMapping(value="/",method=RequestMethod.DELETE )
	public ResponseEntity<?> cambiarEstadoSocio(@RequestBody Socio socio)throws Exception{
		SocioDAO dao = new SocioDAO();
		try {
			socio.asignarFechaRetiro();
			dao.modificarEstado(socio);
			System.out.println("Desde el controler:" + socio.getFechaRetiro());
			return new ResponseEntity<>("Creado",HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	@GetMapping(value = "/reporte/listarSocio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporteListadoSocio()throws Exception{
		byte[] data = null;
		SocioDAO dao = new SocioDAO();
		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();

			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(dao.listar("","")));
			return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(print),HttpStatus.OK);	
		}catch(Exception e) {
			throw e;
		}
	}
}
