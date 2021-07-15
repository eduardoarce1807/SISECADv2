package upeu.sisecad.grupo5.oauth.models;

import lombok.Data;

@Data
public class Docente {
	
	private Integer id;
	private String coDocente;
	private boolean esOrdinario;
	private Integer idCategoria;
	private Integer idUa;
	
}
