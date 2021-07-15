package upeu.sisecad.grupo5.app.docente.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import upeu.sisecad.grupo5.app.docente.entity.Docente;
import upeu.sisecad.grupo5.app.docente.entity.Legajo;

public interface DocenteService {

	public List<Docente> findAll();
	public Docente findById(Integer id);
	public Docente save(Docente docente);
	public void deleteById(Integer id);
	
	List<Map<String, Object>> getConcursos(String codDocente);
	
	Optional<Legajo> getLegajoByIdNomina(Integer idNomina);
	
	void insertLegajo(Integer idNomina);
	
	Integer maxIdLegajo();
	
}
