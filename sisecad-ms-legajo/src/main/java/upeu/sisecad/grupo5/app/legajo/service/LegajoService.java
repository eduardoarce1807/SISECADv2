package upeu.sisecad.grupo5.app.legajo.service;

import java.util.List;
import java.util.Map;

import upeu.sisecad.grupo5.app.legajo.entity.Capitulo;
import upeu.sisecad.grupo5.app.legajo.entity.Factor;
import upeu.sisecad.grupo5.app.legajo.entity.Formulario;
import upeu.sisecad.grupo5.app.legajo.entity.Item;
import upeu.sisecad.grupo5.app.legajo.entity.Legajo;

public interface LegajoService {

	public List<Legajo> findAll();
	public Legajo findById(Integer id);
	public Legajo save(Legajo legajo);
	public void deleteById(Integer id);
	
	public List<Capitulo> getCapitulos();
	public Capitulo getCapituloById(Integer id);
	public List<Factor> getFactorByIdCapitulo(Integer id);
	
	public Map<String, Integer> getModuloSuma(Integer id);
	
	public List<Item> getFormulario(Integer id);
	
	public List<Map<String, Object>> getInfoFormulario(Integer id);
	
	//aux
	public Integer maxIdFormulario();

	public void addFormulario(Integer idFactor, Formulario form);
	
	//Puntaje
	public void getPuntaje(Integer idFactor, Formulario form);

	
}
