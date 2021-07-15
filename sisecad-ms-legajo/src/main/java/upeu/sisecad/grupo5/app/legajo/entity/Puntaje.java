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
@Table(name = "tbl_capitulo")
public class Puntaje implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_puntaje")
	private Integer id;
	
	@Column(name = "id_t_puntaje")
	private Integer idTPuntaje;
	
	@Column(name = "in_puntaje")
	private Integer inPuntaje;
	
}
