package upeu.sisecad.grupo5.app.legajo.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import upeu.sisecad.grupo5.app.legajo.entity.Capitulo;
import upeu.sisecad.grupo5.app.legajo.entity.Factor;
import upeu.sisecad.grupo5.app.legajo.entity.Item;
import upeu.sisecad.grupo5.app.legajo.entity.Legajo;

public interface LegajoDao extends CrudRepository<Legajo, Integer> {

	// Capitulo

	@Query(value = "select c from Capitulo c")
	List<Capitulo> getCapitulos();

	@Query(value = "select c from Capitulo c where c.id = ?1")
	Capitulo getCapituloById(Integer id);

	// Factor

	@Query(value = "select f from Factor f where idCapitulo = ?1")
	List<Factor> getFactorByIdCapitulo(Integer id);

	@Query(value = "select cp.id_legajos_cap as idLegajoCap, cp.id_capitulo as idCapitulo, cp.id_legajo as idLegajo, p.in_puntaje as inPuntaje \n"
			+ "from tbl_capitulo_puntaje cp join tca_puntaje p on cp.id_puntaje = p.id_puntaje \n"
			+ "     where cp.id_legajo = ?1", nativeQuery = true)
	Map<String, Integer> getModuloSuma(Integer id);

	@Query(value = "select i from Item i where idFactor = ?1")
	List<Item> getFormulario(Integer id);

	@Query(value = "select form.no_grado, form.centro_estudios, pun.in_puntaje, it.no_item grado, it.no_abreviatura,\n"
			+ "form.url_archivo from tbl_formulario_puntaje f \n"
			+ "join tbl_factor fac on f.id_factor = fac.id_factor \n"
			+ "join tbl_formulario form on form.id_formulario = f.id_formulario\n"
			+ "join tca_puntaje pun on pun.id_puntaje = f.id_puntaje\n"
			+ "join tbl_item it on it.id_item = form.id_item\n"
			+ "where f.id_factor = 1  and form.no_grado is not null", nativeQuery = true)
	List<Map<String, Object>> queryGrados();

	@Query(value = "select form.mencion_titulo, form.centro_estudios, pun.in_puntaje, form.url_archivo from tbl_formulario_puntaje f\n"
			+ "	join tbl_factor fac on f.id_factor = fac.id_factor\n"
			+ "	join tbl_formulario form on form.id_formulario = f.id_formulario\n"
			+ "	join tca_puntaje pun on pun.id_puntaje = f.id_puntaje\n"
			+ "	join tbl_item it on it.id_item = form.id_item\n"
			+ "	where f.id_factor = 2 and form.id_item is not null", nativeQuery = true)
	List<Map<String, Object>> queryTitulos();

	@Query(value = "select form.especialidad, form.centro_estudios, pun.in_puntaje, form.url_archivo from tbl_formulario_puntaje f\n"
			+ "	join tbl_factor fac on f.id_factor = fac.id_factor\n"
			+ "	join tbl_formulario form on form.id_formulario = f.id_formulario\n"
			+ "	join tca_puntaje pun on pun.id_puntaje = f.id_puntaje\n"
			+ "	join tbl_item it on it.id_item = form.id_item\n"
			+ "	where f.id_factor = 3 and form.especialidad is not null", nativeQuery = true)
	List<Map<String, Object>> queryEspecializacion();

	@Query(value = "select form.centro_estudios, pun.in_puntaje, form.pais, form.no_estudios, form.url_archivo from tbl_formulario_puntaje f\n"
			+ "	join tbl_factor fac on f.id_factor = fac.id_factor\n"
			+ "	join tbl_formulario form on form.id_formulario = f.id_formulario\n"
			+ "	join tca_puntaje pun on pun.id_puntaje = f.id_puntaje\n"
			+ "	join tbl_item it on it.id_item = form.id_item\n"
			+ "	where f.id_factor = 4 and form.no_estudios is not null", nativeQuery = true)
	List<Map<String, Object>> queryEstudios();

	@Query(value = "select form.centro_estudios, form.idioma, it.no_item nivel, pun.in_puntaje, form.url_archivo from tbl_formulario_puntaje f\n"
			+ "	join tbl_factor fac on f.id_factor = fac.id_factor\n"
			+ "	join tbl_formulario form on form.id_formulario = f.id_formulario\n"
			+ "	join tca_puntaje pun on pun.id_puntaje = f.id_puntaje\n"
			+ "	join tbl_item it on it.id_item = form.id_item\n"
			+ "	where f.id_factor = 6 and form.idioma is not null", nativeQuery = true)
	List<Map<String, Object>> queryIdiomas();

	// aux para saber el max id_formulario
	@Query(value = "SELECT MAX(id_formulario) FROM tbl_formulario", nativeQuery = true)
	Integer maxIdFormulario();

	@Query(value = "insert into tbl_formulario (id_formulario, id_item, no_grado, centro_estudios, a??os,\n"
			+ " url_archivo) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
	void queryGrados(Integer idFormulario, Integer idItem, String noGrado, String ceEstudios, Integer years,
			String url);

	@Query(value = "insert into tbl_formulario (id_formulario, id_item, mencion_titulo, centro_estudios, a??os,\n"
			+ " url_archivo) values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
	void queryTitulos(Integer idFormulario, Integer idItem, String mencionTitulo, String ceEstudios, Integer years,
			String url);

	@Query(value = "insert into tbl_formulario (id_formulario, id_item, especialidad, centro_estudios, a??os,\n"
			+ " creditos, url_archivo) values(?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	void queryEspecializacion(Integer idFormulario, Integer idItem, String especialidad, String ceEstudios,
			Integer years, Integer creditos, String url);

	@Query(value = "insert into tbl_formulario (id_formulario, id_item, pais, a??os, no_estudios, \n"
			+ " centro_estudios, creditos, url_archivo) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	void queryEstudios(Integer idFormulario, Integer idItem, String pais, Integer years, String noEstudios,
			String ceEstudios, Integer creditos, String url);

	@Query(value = "insert into tbl_formulario (id_formulario, id_item, centro_estudios, lengua_materna, idioma, \n"
			+ " unidad, url_archivo) values(?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	void queryIdiomas(Integer idFormulario, Integer idItem, String ceEstudios, String lenguaMaterna, String idioma,
			Integer unidad, String url);

	// Puntos

	@Query(value = "select puntos from tbl_item where id_item = ?1", nativeQuery = true)
	Integer queryPuntos(Integer idItem);

	// Insert puntaje

	@Query(value = "insert into tca_puntaje(id_puntaje, id_t_puntaje, in_puntaje) values((select max(id_puntaje) from tca_puntaje)+1, 1, ?1);\n"
			+ "insert into tca_puntaje(id_puntaje, id_t_puntaje, in_puntaje) values((select max(id_puntaje) from tca_puntaje)+1, 2, ?1);\n"
			+ "insert into tca_puntaje(id_puntaje, id_t_puntaje, in_puntaje) values((select max(id_puntaje) from tca_puntaje)+1, 3, ?1);", nativeQuery = true)
	void queryInsertPuntaje(double result);

	@Query(value = "insert into tca_puntaje values((select max(id_puntaje) from tca_puntaje)+1, 1, ?1)", nativeQuery = true)
	void queryInsertPuntajeSingle(Integer idPuntaje, double result);

	////

	@Query(value = "select * from tca_puntaje t where t.in_puntaje = ?1 ORDER BY t.id_puntaje ASC LIMIT 3", nativeQuery = true)
	List<Map<String, Integer>> queryList(double result);

	@Query(value = "SELECT MAX(id_puntaje) AS id_puntaje FROM tca_puntaje", nativeQuery = true)
	Integer maxIdPuntaje();

	@Query(value = "SELECT MAX(id_puntaje) AS id_puntaje FROM tca_puntaje where id_t_puntaje = 1", nativeQuery = true)
	Integer maxIdPuntajeTipo1();

	@Query(value = "SELECT MAX(id_puntaje) AS id_puntaje FROM tca_puntaje where id_t_puntaje = 2", nativeQuery = true)
	Integer maxIdPuntajeTipo2();

	@Query(value = "SELECT MAX(id_puntaje) AS id_puntaje FROM tca_puntaje where id_t_puntaje = 3", nativeQuery = true)
	Integer maxIdPuntajeTipo3();

	@Query(value = "SELECT MAX(id_form_puntaje) AS id_form_puntaje FROM tbl_formulario_puntaje", nativeQuery = true)
	Integer maxIdFormularioPuntaje();

	@Query(value = "insert into tbl_formulario_puntaje values(?1, ?2, ?3, ?4, false)", nativeQuery = true)
	void queryInsertFormularioPuntaje(Integer idFormularioPuntaje, Integer idFormulario, Integer idPuntaje,
			Integer idFactor);

	@Query(value = "SELECT MAX(id_factor_puntaje) AS id_factor_puntaje FROM tbl_factor_puntaje", nativeQuery = true)
	Integer maxIdFactorPuntaje();

	@Query(value = "insert into tbl_factor_puntaje values(?1, ?2, ?3, true)", nativeQuery = true)
	void queryInsertFactorPuntaje(Integer idFactorPuntaje, Integer idFactor, Integer idPuntaje);

	@Query(value = "SELECT MAX(id_legajos_cap) AS id_legajos_cap FROM tbl_capitulo_puntaje", nativeQuery = true)
	Integer maxIdCapituloPuntaje();

	@Query(value = "insert into tbl_capitulo_puntaje values (?1, ?2, ?3, ?4, false)", nativeQuery = true)
	void queryInsertCapituloPuntaje(Integer idCapituloPuntaje, Integer idLegajo, Integer idCapitulo, Integer idPuntaje);

}
