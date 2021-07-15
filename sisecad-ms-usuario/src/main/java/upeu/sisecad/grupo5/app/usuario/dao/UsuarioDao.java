package upeu.sisecad.grupo5.app.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import upeu.sisecad.grupo5.app.usuario.entity.Docente;
import upeu.sisecad.grupo5.app.usuario.entity.Persona;
import upeu.sisecad.grupo5.app.usuario.entity.Usuario;



@RepositoryRestResource(path="usuario")
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{

	@RestResource(path = "buscar-username")
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerUsuario(@Param("username") String username);
	
	@RestResource(path = "buscar-idp-u")
	@Query("select p from Persona p inner join Usuario u on u.id = p.id where u.username = ?1")
	public Persona obtenerPersonaByUsername(@Param("username") String username);
	
	@RestResource(path = "buscar-idd-u")
	@Query("select d from Docente d inner join Persona p on p.id = d.id inner join Usuario u on u.id = p.id where u.username = ?1")
	public Docente obtenerDocenteByUsername(@Param("username") String username);
	
}
