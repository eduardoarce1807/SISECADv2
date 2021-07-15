package upeu.sisecad.grupo5.app.docente.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_legajo")
public class Legajo {
	
	@Id
	@Column(name = "id_legajo")
	private Integer id;
	@Column(name = "id_nomina")
	private Integer idNomina;
	@Column(name = "id_puntaje")
	private Integer idPuntaje;
	@Column(name = "es_revisado")
	private boolean esRevisado;

}
