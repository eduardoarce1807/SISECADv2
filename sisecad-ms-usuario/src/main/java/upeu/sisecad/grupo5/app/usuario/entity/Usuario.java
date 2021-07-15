package upeu.sisecad.grupo5.app.usuario.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;
	
	@Column(name = "us_nombre",length = 20,unique = true)
	private String username;
	
	@Column(name = "pw_usuario", length = 60)
	private String password;
	
	@Column(name = "es_usuario")
	private Boolean estado;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tbl_usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"), 
			inverseJoinColumns = @JoinColumn(name="id_rol"))
	private List<Rol> roles;
	

}
