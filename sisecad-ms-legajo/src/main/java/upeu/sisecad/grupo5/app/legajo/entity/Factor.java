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
@Table(name = "tbl_factor")
public class Factor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_factor")
	private Integer id;
	@Column(name = "no_factor")
	private String noFactor;
	@Column(name = "id_capitulo")
	private Integer idCapitulo;
	@Column(name = "en_foto")
	private String enFoto;

}
