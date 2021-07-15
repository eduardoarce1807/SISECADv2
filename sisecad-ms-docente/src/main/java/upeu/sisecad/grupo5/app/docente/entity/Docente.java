package upeu.sisecad.grupo5.app.docente.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tbl_docente")
public class Docente {
	
	@Id
	@Column(name = "id_persona")
	private Integer id;
	@Column(name = "co_docente")
	private String coDocente;
	@Column(name = "es_ordinario")
	private boolean esOrdinario;
	@Column(name = "id_categoria")
	private Integer idCategoria;
	@Column(name = "id_ua")
	private Integer idUa;

}
