package upeu.sisecad.grupo5.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import upeu.sisecad.grupo5.oauth.models.Persona;
import upeu.sisecad.grupo5.oauth.models.Usuario;
import upeu.sisecad.grupo5.oauth.services.IUsuarioService;

@Component
public class InfoAditionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Persona persona = usuarioService.findPersonaByUsername(usuario.getUsername());
		info.put("username", usuario.getUsername());
		info.put("persona", persona);
		info.put("roles", usuario.getRoles());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

	
}
