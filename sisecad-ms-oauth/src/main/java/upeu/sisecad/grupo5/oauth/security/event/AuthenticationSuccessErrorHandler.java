package upeu.sisecad.grupo5.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;
import upeu.sisecad.grupo5.oauth.models.Usuario;
import upeu.sisecad.grupo5.oauth.services.IUsuarioService;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if(authentication.getName().equals("lucaz") || authentication.getName().equals("sanny")) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		
		
			Usuario usuario = usuarioService.findByUsername(user.getUsername());
			
			if(usuario.getIntentos() != null && usuario.getIntentos()>0) {
				usuario.setIntentos(0);
				usuarioService.update(usuario, usuario.getId());
			}
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el login: " + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);
		
		if(authentication.getName().equals("lucaz") || authentication.getName().equals("sanny")) {
			try {
				Usuario usuario = usuarioService.findByUsername(authentication.getName());
				if(usuario.getIntentos()==null) {
					usuario.setIntentos(0);
				}
				
				log.info("Intento Actual N°: "+usuario.getIntentos());
				usuario.setIntentos(usuario.getIntentos()+1);
				log.info("Intento Despues N°: "+usuario.getIntentos());
				
				if(usuario.getIntentos()>=3) {
					log.error(String.format("El usuario %s deshabilitado por 3 intentos fallidos.", usuario.getUsername()));
					usuario.setEstado(false);
				}
				
			} catch(FeignException e) {
				log.error(String.format("El usuario %s no existe en el sistema.", authentication.getName()));
			}
		}
		
	}
	
}
