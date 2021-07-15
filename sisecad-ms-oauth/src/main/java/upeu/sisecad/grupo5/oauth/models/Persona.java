package upeu.sisecad.grupo5.oauth.models;

import java.util.Date;

import lombok.Data;

@Data
public class Persona {

	private Integer id;
	private String nombre;
	private String paterno;
	private String materno;
	private String dni;
	private String telefono;
	private String email;
	private Date nacimiento;
	private String direccion;
	private String foto;
	
}
