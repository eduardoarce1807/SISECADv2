package upeu.sisecad.grupo5.app.legajo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_formulario")
public class Formulario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_formulario")
	private Integer id;
	
	@Column(name = "id_item")
	private Integer idItem;
	
	@Column(name = "url_archivo")
	private String urlArchivo;
	
	@Column(name = "no_grado")
	private String noGrado;
	
	@Column(name = "centro_estudio")
	private String centroEstudios;
	
	@Column(name = "años")
	private Integer years;
	
	@Column(name = "mencion_titulo")
	private String mencionTitulo;
	
	@Column(name = "especialidad")
	private String especialidad;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "creditos")
	private Integer creditos;
	
	@Column(name = "semestre")
	private Integer semestre;
	
	@Column(name = "unidad")
	private Integer unidad;
	
	@Column(name = "noEstudios")
	private String noEstudios;
	
	@Column(name = "lenguaMaterna")
	private String lenguaMaterna;
	
	@Column(name = "idioma")
	private String idioma;
	
	
}
