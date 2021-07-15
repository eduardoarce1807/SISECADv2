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
@Table(name = "tbl_item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_item")
	private Integer id;
	
	@Column(name = "no_item")
	private String noItem;
	
	@Column(name = "de_requerimiento")
	private String deRequerimiento;
	
	@Column(name = "puntos")
	private Integer puntos;
	
	@Column(name = "id_factor")
	private Integer idFactor;
	
}
