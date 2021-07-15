package upeu.sisecad.grupo5.app.docente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import upeu.sisecad.grupo5.app.docente.entity.Docente;
import upeu.sisecad.grupo5.app.docente.entity.Legajo;
import upeu.sisecad.grupo5.app.docente.entity.idnomina;
import upeu.sisecad.grupo5.app.docente.service.DocenteService;

@RestController
public class DocenteController {

	@Autowired
	private DocenteService docenteService;

	@GetMapping
	public List<Docente> listar() {
		return docenteService.findAll();
	}

	@GetMapping("/{id}")
	public Docente findById(@PathVariable Integer id) {
		return docenteService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Docente crear(@RequestBody Docente docente) {
		return docenteService.save(docente);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Docente editar(@RequestBody Docente docente, @PathVariable Integer id) {
		Docente d = docenteService.findById(id);
		
		d.setCoDocente(docente.getCoDocente());
		d.setEsOrdinario(docente.isEsOrdinario());
		d.setIdCategoria(docente.getIdCategoria());
		d.setIdUa(docente.getIdUa());
		
		return docenteService.save(d);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Integer id) {
		docenteService.deleteById(id);
	}
	
	//
	
	@GetMapping("/concurso/{coDocente}")
	public List<Map<String, Object>> getConcursos(@PathVariable String coDocente) {
		return docenteService.getConcursos(coDocente);
	}
	
	@PostMapping("/concurso")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> addLegajos(@RequestBody idnomina idnomina) {
		Legajo legNull = new Legajo(null, null, null, false);
		Legajo leg = docenteService.getLegajoByIdNomina(idnomina.getIdnomina()).orElse(legNull);
		Integer currID = docenteService.maxIdLegajo();
		HashMap<String, Object> map = new HashMap<>();
		
		if(leg.getId() == null) {
			//Insert
			try {
				docenteService.insertLegajo(idnomina.getIdnomina());
			} catch (Exception e) {
				
				if(docenteService.maxIdLegajo() == currID) {
					map.put("message", "Error, esa nómina no existe.");
				}else {
					map.put("message", "Creado correctamente.");
					map.put("idlegajos", docenteService.maxIdLegajo());
				}
			}
		    
		    return map;
		}else {
			
			map.put("message", "Ya existe un ID creado, puede continuar.");
		    map.put("idlegajos", docenteService.maxIdLegajo());
		    return map;
		    
		}
		
	 
	}

}
