package upeu.sisecad.grupo5.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import upeu.sisecad.grupo5.oauth.models.Persona;
import upeu.sisecad.grupo5.oauth.models.Usuario;

@FeignClient(name = "ms-usuario")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuario/search/buscar-username")
	public Usuario findByUsername(@RequestParam String username);

	@GetMapping("/usuario/search/buscar-idp-u")
	public Persona findPersonaByUsername(@RequestParam String username);
	
	@PutMapping("/usuario/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer id);
	
}
