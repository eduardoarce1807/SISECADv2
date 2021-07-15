package upeu.sisecad.grupo5.oauth.services;

import org.springframework.web.bind.annotation.RequestParam;

import upeu.sisecad.grupo5.oauth.models.Docente;
import upeu.sisecad.grupo5.oauth.models.Persona;
import upeu.sisecad.grupo5.oauth.models.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Persona findPersonaByUsername(String username);
	
	public Docente findDocenteByUsername(String username);
	
	public Usuario update(Usuario usuario, Integer id);
	
}
