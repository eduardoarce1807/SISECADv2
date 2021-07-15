package upeu.sisecad.grupo5.oauth.models;

import java.util.List;

import lombok.Data;


@Data
public class Usuario {

	private Integer id;
	private String username;
	private String password;
	private Boolean estado;
	private Integer intentos;
	private List<Rol> roles;
}
