package upeu.sisecad.grupo5.app.usuario.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mae_persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_persona")
	private Integer id;
	@Column(name = "no_persona")
	private String nombre;
	@Column(name = "ap_paterno")
	private String paterno;
	@Column(name = "ap_materno")
	private String materno;
	@Column(name = "co_dni")
	private String dni;
	@Column(name = "de_telefono")
	private String telefono;
	@Column(name = "de_email")
	private String email;
	@Column(name = "fe_nacimiento")
	private Date nacimiento;
	@Column(name = "di_personal")
	private String direccion;
	@Column(name = "en_foto")
	private String foto;
	
}
