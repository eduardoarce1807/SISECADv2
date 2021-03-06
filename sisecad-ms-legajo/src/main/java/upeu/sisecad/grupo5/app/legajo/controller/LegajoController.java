package upeu.sisecad.grupo5.app.legajo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import upeu.sisecad.grupo5.app.legajo.entity.Capitulo;
import upeu.sisecad.grupo5.app.legajo.entity.Factor;
import upeu.sisecad.grupo5.app.legajo.entity.Formulario;
import upeu.sisecad.grupo5.app.legajo.entity.Item;
import upeu.sisecad.grupo5.app.legajo.entity.Legajo;
import upeu.sisecad.grupo5.app.legajo.service.LegajoService;

@RestController
public class LegajoController {

	@Autowired
	private LegajoService legajoService;

	@GetMapping
	public List<Legajo> listar() {
		return legajoService.findAll();
	}

	@GetMapping("/{id}")
	public Legajo findById(@PathVariable Integer id) {
		return legajoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Legajo crear(@RequestBody Legajo legajo) {
		return legajoService.save(legajo);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Legajo editar(@RequestBody Legajo legajo, @PathVariable Integer id) {
		Legajo l = legajoService.findById(id);

		l.setIdNomina(legajo.getIdNomina());
		l.setIdPuntaje(legajo.getIdPuntaje());
		l.setEsRevisado(legajo.getEsRevisado());

		return legajoService.save(l);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Integer id) {
		legajoService.deleteById(id);
	}
	
	@GetMapping("/capitulos")
	public List<Capitulo> getCapitulos() {
		return legajoService.getCapitulos();
	}
	
	@GetMapping("/capitulos/{id}")
	public Capitulo getCapituloById(@PathVariable Integer id) {
		return legajoService.getCapituloById(id);
	}
	
	@GetMapping("/capitulos/factores/{id}")
	public List<Factor> getFactorByIdCapitulo(@PathVariable Integer id) {
		return legajoService.getFactorByIdCapitulo(id);
	}
	
	@GetMapping("/capitulos/factores/puntaje/{id}")
	public Map<String, Integer> getModuloSuma(@PathVariable Integer id) {
		return legajoService.getModuloSuma(id);
	}
	
	//Formulario
	
	@GetMapping("/formulario/{idFactor}")
	public Map<String, Object> getFormulario(@PathVariable Integer idFactor) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("select", legajoService.getFormulario(idFactor));
		map.put("info", legajoService.getInfoFormulario(idFactor));
		
		return map;
	}
	
	@PostMapping("/formulario/{idFactor}")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> addFormulario(@PathVariable Integer idFactor, @RequestBody Formulario formulario) {
		
		HashMap<String, Object> map = new HashMap<>();
		Integer currID = legajoService.maxIdFormulario();
		
		try {
			legajoService.addFormulario(idFactor, formulario);
		} catch (Exception e) {
			
			try {
				legajoService.getPuntaje(idFactor, formulario);
			} catch (Exception e2) {
				map.put("getPuntaje", "Error.");
			}
			
			if(legajoService.maxIdFormulario() == currID) {
				map.put("message", "Error.");
			}else {
				map.put("message", "Guardado correctamente.");
			}
		}
		
		return map;
	}

}
