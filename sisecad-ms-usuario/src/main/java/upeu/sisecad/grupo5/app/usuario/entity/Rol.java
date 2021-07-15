package upeu.sisecad.grupo5.app.usuario.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "tbl_rol")
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long id;
	
	@Column(name = "no_rol", unique = true, length = 30)
	private String nombre;
	
	@Column(name = "es_rol")
	private Boolean estado;
}
