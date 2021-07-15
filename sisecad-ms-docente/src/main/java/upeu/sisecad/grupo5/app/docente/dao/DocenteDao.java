package upeu.sisecad.grupo5.app.docente.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import upeu.sisecad.grupo5.app.docente.entity.Docente;
import upeu.sisecad.grupo5.app.docente.entity.Legajo;

public interface DocenteDao extends CrudRepository<Docente, Integer>{

	@Query(value = "select n.id_nomina,  n.id_concurso , c.fe_inicio, c.fe_fin, c.ti_modalidad,\n"
			+ "c.ti_participacion, c.fi_req,fi_bases, c.fi_guia, d.co_docente\n"
			+ "from tbl_nomina n\n"
			+ "join tbl_concurso c on n.id_concurso = c.id_concurso\n"
			+ "join tbl_docente d on n.co_docente = d.co_docente\n"
			+ "where d.co_docente = ?1", nativeQuery = true)
	List<Map<String, Object>> getConcursos(String codDocente);
	
	//
	
	@Query(value = "select l from Legajo l where idNomina = ?1")
	Optional<Legajo> getLegajoByIdNomina(Integer idNomina);
	
	@Query(value = "insert into tbl_legajo values((select max(id_legajo) from tbl_legajo)+1, ?1, null, false)", nativeQuery = true)
	void insertLegajo(Integer idNomina);
	
	@Query(value = "SELECT MAX(id_legajo) FROM tbl_legajo", nativeQuery = true)
	Integer maxIdLegajo();
	
	
}
