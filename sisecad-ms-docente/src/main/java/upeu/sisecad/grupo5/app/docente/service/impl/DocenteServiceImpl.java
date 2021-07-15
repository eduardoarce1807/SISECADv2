package upeu.sisecad.grupo5.app.docente.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upeu.sisecad.grupo5.app.docente.dao.DocenteDao;
import upeu.sisecad.grupo5.app.docente.entity.Docente;
import upeu.sisecad.grupo5.app.docente.entity.Legajo;
import upeu.sisecad.grupo5.app.docente.service.DocenteService;

@Service
public class DocenteServiceImpl implements DocenteService {

	@Autowired
	private DocenteDao docenteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Docente> findAll() {
		return (List<Docente>) docenteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Docente findById(Integer id) {
		return docenteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Docente save(Docente docente) {
		return docenteDao.save(docente);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		docenteDao.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> getConcursos(String codDocente) {
		return docenteDao.getConcursos(codDocente);
	}
	
	@Override
	public Optional<Legajo> getLegajoByIdNomina(Integer idNomina) {
		return docenteDao.getLegajoByIdNomina(idNomina);
	}

	@Override
	public void insertLegajo(Integer idNomina) {
		docenteDao.insertLegajo(idNomina);
	}

	@Override
	public Integer maxIdLegajo() {
		return docenteDao.maxIdLegajo();
	}

}
