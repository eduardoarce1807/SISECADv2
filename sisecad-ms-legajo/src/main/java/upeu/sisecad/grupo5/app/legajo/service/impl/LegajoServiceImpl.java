package upeu.sisecad.grupo5.app.legajo.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import upeu.sisecad.grupo5.app.legajo.dao.LegajoDao;
import upeu.sisecad.grupo5.app.legajo.entity.Capitulo;
import upeu.sisecad.grupo5.app.legajo.entity.Factor;
import upeu.sisecad.grupo5.app.legajo.entity.Formulario;
import upeu.sisecad.grupo5.app.legajo.entity.Item;
import upeu.sisecad.grupo5.app.legajo.entity.Legajo;
import upeu.sisecad.grupo5.app.legajo.service.LegajoService;

@Service
public class LegajoServiceImpl implements LegajoService {

	@Autowired
	private LegajoDao legajoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Legajo> findAll() {
		return (List<Legajo>) legajoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Legajo findById(Integer id) {
		return legajoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Legajo save(Legajo legajo) {
		return legajoDao.save(legajo);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		legajoDao.deleteById(id);
	}

	@Override
	public List<Capitulo> getCapitulos() {
		return legajoDao.getCapitulos();
	}

	@Override
	public Capitulo getCapituloById(Integer id) {
		return legajoDao.getCapituloById(id);
	}

	@Override
	public List<Factor> getFactorByIdCapitulo(Integer id) {
		return legajoDao.getFactorByIdCapitulo(id);
	}

	@Override
	public Map<String, Integer> getModuloSuma(Integer id) {

		return legajoDao.getModuloSuma(id);
	}

	@Override
	public List<Item> getFormulario(Integer id) {
		return legajoDao.getFormulario(id);
	}

	@Override
	public List<Map<String, Object>> getInfoFormulario(Integer id) {
		
		if(id == 1) {
			return legajoDao.queryGrados();
		} else {
			if(id == 2) {
				return legajoDao.queryTitulos();
			} else {
				if(id == 3) {
					return legajoDao.queryEspecializacion();
				} else {
					if(id == 4) {
						return legajoDao.queryEstudios();
					} else {
						if(id == 6) {
							return legajoDao.queryIdiomas();
						} else {
							return null;
						}
					}
				}
			}
		}
		
	}
	
	@Override
	public Integer maxIdFormulario() {
		return legajoDao.maxIdFormulario();
	}

	@Override
	public void addFormulario(Integer idFactor, Formulario form) {
		
		Integer maxIdFormulario = legajoDao.maxIdFormulario();
		Integer idFormulario = 0;
		
		if(maxIdFormulario == null) {
			idFormulario = 1;
		}else {
			idFormulario = maxIdFormulario+1;
		}
		
		if(idFactor == 1) {
			
			legajoDao.queryGrados(
					idFormulario,
					form.getIdItem(),
					form.getNoGrado(),
					form.getCentroEstudios(),
					form.getYears(),
					form.getUrlArchivo());
		}
		if(idFactor == 2) {
			legajoDao.queryTitulos(
					idFormulario,
					form.getIdItem(),
					form.getMencionTitulo(),
					form.getCentroEstudios(),
					form.getYears(),
					form.getUrlArchivo());
		}
		if(idFactor == 3) {
			legajoDao.queryEspecializacion(
					idFormulario,
					form.getIdItem(),
					form.getEspecialidad(),
					form.getCentroEstudios(),
					form.getYears(),
					form.getCreditos(), 
					form.getUrlArchivo());
		}
		if(idFactor == 4) {
			legajoDao.queryEstudios(
					idFormulario,
					form.getIdItem(),
					form.getPais(), 
					form.getYears(),
					form.getNoEstudios(),
					form.getCentroEstudios(),
					form.getCreditos(),
					form.getUrlArchivo());
		}
		if(idFactor == 6) {
			legajoDao.queryIdiomas(
					idFormulario,
					form.getIdItem(),
					form.getCentroEstudios(),
					form.getLenguaMaterna(),
					form.getIdioma(),
					form.getUnidad(),
					form.getUrlArchivo());
		}
		
	}

	@Override
	public void getPuntaje(Integer idFactor, Formulario form) {
		switch (idFactor) {
		case 1:
			List<Map<String, Object>> queryGrados = legajoDao.queryGrados();

			System.out.println("Size: " + queryGrados.size());

			if (queryGrados.size() == 0) {

				System.out.println("vacio");
				Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
				Integer a??os = form.getYears();
				Integer result = puntaje * a??os;
				System.out.println("Result: " + result);
				//
				Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
				Integer idPuntaje = 0;
				if (maxIdPuntaje == null) {
					idPuntaje = 1;
				} else {
					idPuntaje = maxIdPuntaje + 1;
				}
				try {

					legajoDao.queryInsertPuntaje(result);

				} catch (Exception e) {
					System.out.print("Error 0 :V");
					List<Map<String, Integer>> list = legajoDao.queryList(result);
					System.out.println("0: " + list.get(0).get("id_t_puntaje"));
					System.out.println("1: " + list.get(1).get("id_t_puntaje"));
					System.out.println("2: " + list.get(2).get("id_t_puntaje"));

					for (int index = 0; index < list.size(); index++) {
						Integer element = list.get(index).get("id_t_puntaje");
						if (element == 1) {
							System.out.println("Entrando a la 1.");
							Integer id1 = legajoDao.maxIdPuntajeTipo1();
							Integer idformulario = legajoDao.maxIdFormulario();
							Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
							Integer idFormularioPuntaje = 0;
							if (maxIdFormularioPuntaje == null) {
								idFormularioPuntaje = 1;
							} else {
								idFormularioPuntaje = maxIdFormularioPuntaje + 1;
							}
							try {
								legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
										idFactor);
							} catch (Exception e2) {
								System.out.print("Error1 :V");

							}
						} else if (element == 2) {
							Integer id2 = legajoDao.maxIdPuntajeTipo2();
							System.out.println("id2: " + id2);
							Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
							Integer idFactorPuntaje = 0;
							if (maxIdFactorPuntaje == null) {
								idFactorPuntaje = 1;
							} else {
								idFactorPuntaje = maxIdFactorPuntaje + 1;
							}
							try {
								legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
							} catch (Exception e3) {
								System.out.print("Error2 :V");
							}

						} else if (element == 3) {
							Integer id3 = legajoDao.maxIdPuntajeTipo3();
							Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
							Integer idCapituloPuntaje = 0;
							if (maxIdCapituloPuntaje == null) {
								idCapituloPuntaje = 1;
							} else {
								idCapituloPuntaje = maxIdCapituloPuntaje + 1;
							}
							try {
								legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
							} catch (Exception e4) {
								System.out.print("Error3 :V");
							}
						}

						System.out.println("Element: " + element);

					}
				}

			} else {
				Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
				Integer a??os = form.getYears();
				Integer result = puntaje * a??os;
				System.out.println("ResultadoElse: " + result);
				//
				Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
				Integer idPuntaje = 0;
				if (maxIdPuntaje == null) {
					idPuntaje = 1;
				} else {
					idPuntaje = maxIdPuntaje + 1;
				}
				try {

					legajoDao.queryInsertPuntajeSingle(idPuntaje, result);

				} catch (Exception e) {
					Integer idformulario = legajoDao.maxIdFormulario();
					Integer idpuntaje = legajoDao.maxIdPuntaje();
					Integer idfactor = idFactor;
					Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
					Integer idFormularioPuntaje = 0;
					if (maxIdFormularioPuntaje == null) {
						idFormularioPuntaje = 1;
					} else {
						idFormularioPuntaje = maxIdFormularioPuntaje + 1;
					}
					try {
						legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje, idfactor);
					} catch (Exception e2) {
						System.out.print("Error2 :V");
					}
				}

			}

			break;

		case 2:
			List<Map<String, Object>> queryTitulos = legajoDao.queryTitulos();

			System.out.println("Size: " + queryTitulos.size());

			if (queryTitulos.size() == 0) {

				System.out.println("vacio");
				Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
				Integer a??os = form.getYears();
				Integer result = puntaje * a??os;
				System.out.println("Result: " + result);

				//
				Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
				Integer idPuntaje = 0;
				if (maxIdPuntaje == null) {
					idPuntaje = 1;
				} else {
					idPuntaje = maxIdPuntaje + 1;
				}
				try {

					legajoDao.queryInsertPuntaje(result);

				} catch (Exception e) {
					System.out.print("Error 0 :V");
					List<Map<String, Integer>> list = legajoDao.queryList(result);
					System.out.println("0: " + list.get(0).get("id_t_puntaje"));
					System.out.println("1: " + list.get(1).get("id_t_puntaje"));
					System.out.println("2: " + list.get(2).get("id_t_puntaje"));

					for (int index = 0; index < list.size(); index++) {
						Integer element = list.get(index).get("id_t_puntaje");
						if (element == 1) {
							System.out.println("Entrando a la 1.");
							Integer id1 = legajoDao.maxIdPuntajeTipo1();
							Integer idformulario = legajoDao.maxIdFormulario();
							Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
							Integer idFormularioPuntaje = 0;
							if (maxIdFormularioPuntaje == null) {
								idFormularioPuntaje = 1;
							} else {
								idFormularioPuntaje = maxIdFormularioPuntaje + 1;
							}
							try {
								legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
										idFactor);
							} catch (Exception e2) {
								System.out.print("Error1 :V");

							}
						} else if (element == 2) {
							Integer id2 = legajoDao.maxIdPuntajeTipo2();
							System.out.println("id2: " + id2);
							Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
							Integer idFactorPuntaje = 0;
							if (maxIdFactorPuntaje == null) {
								idFactorPuntaje = 1;
							} else {
								idFactorPuntaje = maxIdFactorPuntaje + 1;
							}
							try {
								legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
							} catch (Exception e3) {
								System.out.print("Error2 :V");
							}

						} else if (element == 3) {
							Integer id3 = legajoDao.maxIdPuntajeTipo3();
							Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
							Integer idCapituloPuntaje = 0;
							if (maxIdCapituloPuntaje == null) {
								idCapituloPuntaje = 1;
							} else {
								idCapituloPuntaje = maxIdCapituloPuntaje + 1;
							}
							try {
								legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
							} catch (Exception e4) {
								System.out.print("Error3 :V");
							}
						}

						System.out.println("Element: " + element);

					}
				}

			} else {
				Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
				Integer a??os = form.getYears();
				Integer result = puntaje * a??os;
				System.out.println("ResultadoElse: " + result);
				//
				Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
				Integer idPuntaje = 0;
				if (maxIdPuntaje == null) {
					idPuntaje = 1;
				} else {
					idPuntaje = maxIdPuntaje + 1;
				}
				try {

					legajoDao.queryInsertPuntajeSingle(idPuntaje, result);

				} catch (Exception e) {
					Integer idformulario = legajoDao.maxIdFormulario();
					Integer idpuntaje = legajoDao.maxIdPuntaje();
					Integer idfactor = idFactor;
					Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
					Integer idFormularioPuntaje = 0;
					if (maxIdFormularioPuntaje == null) {
						idFormularioPuntaje = 1;
					} else {
						idFormularioPuntaje = maxIdFormularioPuntaje + 1;
					}
					try {
						legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje, idfactor);
					} catch (Exception e2) {
						System.out.print("Error2 :V");
					}
				}

			}

			break;

		case 3:
			List<Map<String, Object>> queryEspecializacion = legajoDao.queryEspecializacion();

			System.out.println("Size: " + queryEspecializacion.size());

			if (queryEspecializacion.size() == 0) {
				System.out.println("vacio");
				if (form.getIdItem() == 7 || form.getIdItem() == 8 || form.getIdItem() == 9) {
					System.out.println("Cr??ditos detectados.");
					Integer puntaje3 = legajoDao.queryPuntos(form.getIdItem());
					Integer creditos = form.getCreditos();
					if (creditos <= 23) {
						System.out.println("Menos de 24 cr??ditos.");
						Integer result = 0;
						System.out.println("Puntaje: " + result);

						//
						Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
						Integer idPuntaje = 0;
						if (maxIdPuntaje == null) {
							idPuntaje = 1;
						} else {
							idPuntaje = maxIdPuntaje + 1;
						}
						try {

							legajoDao.queryInsertPuntaje(result);

						} catch (Exception e) {
							System.out.print("Error... :V");
							List<Map<String, Integer>> list = legajoDao.queryList(result);

							for (int index = 0; index < list.size(); index++) {
								Integer element = list.get(index).get("id_t_puntaje");
								if (element == 1) {
									System.out.println("Entrando a la 1.");
									Integer id1 = legajoDao.maxIdPuntajeTipo1();
									Integer idformulario = legajoDao.maxIdFormulario();
									Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
									Integer idFormularioPuntaje = 0;
									if (maxIdFormularioPuntaje == null) {
										idFormularioPuntaje = 1;
									} else {
										idFormularioPuntaje = maxIdFormularioPuntaje + 1;
									}
									try {
										legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
												idFactor);
									} catch (Exception e2) {
										System.out.print("Error1 :V");

									}
								} else if (element == 2) {
									Integer id2 = legajoDao.maxIdPuntajeTipo2();
									System.out.println("id2: " + id2);
									Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
									Integer idFactorPuntaje = 0;
									if (maxIdFactorPuntaje == null) {
										idFactorPuntaje = 1;
									} else {
										idFactorPuntaje = maxIdFactorPuntaje + 1;
									}
									try {
										legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
									} catch (Exception e3) {
										System.out.print("Error2 :V");
									}

								} else if (element == 3) {
									Integer id3 = legajoDao.maxIdPuntajeTipo3();
									Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
									Integer idCapituloPuntaje = 0;
									if (maxIdCapituloPuntaje == null) {
										idCapituloPuntaje = 1;
									} else {
										idCapituloPuntaje = maxIdCapituloPuntaje + 1;
									}
									try {
										legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
									} catch (Exception e4) {
										System.out.print("Error3 :V");
									}
								}

								System.out.println("Element: " + element);

							}
						}

					} else {
						Integer result = puntaje3 * 1;
						System.out.println("Puntaje: " + result);

						//
						Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
						Integer idPuntaje = 0;
						if (maxIdPuntaje == null) {
							idPuntaje = 1;
						} else {
							idPuntaje = maxIdPuntaje + 1;
						}
						try {

							legajoDao.queryInsertPuntaje(result);
							
						} catch (Exception e) {
							System.out.print("Error...2 :V");
							List<Map<String, Integer>> list = legajoDao.queryList(result);
							for (int index = 0; index < list.size(); index++) {
								Integer element = list.get(index).get("id_t_puntaje");
								if (element == 1) {
									System.out.println("Entrando a la 1.");
									Integer id1 = legajoDao.maxIdPuntajeTipo1();
									Integer idformulario = legajoDao.maxIdFormulario();
									Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
									Integer idFormularioPuntaje = 0;
									if (maxIdFormularioPuntaje == null) {
										idFormularioPuntaje = 1;
									} else {
										idFormularioPuntaje = maxIdFormularioPuntaje + 1;
									}
									try {
										legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
												idFactor);
									} catch (Exception e2) {
										System.out.print("Error1 :V");

									}
								} else if (element == 2) {
									Integer id2 = legajoDao.maxIdPuntajeTipo2();
									System.out.println("id2: " + id2);
									Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
									Integer idFactorPuntaje = 0;
									if (maxIdFactorPuntaje == null) {
										idFactorPuntaje = 1;
									} else {
										idFactorPuntaje = maxIdFactorPuntaje + 1;
									}
									try {
										legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
									} catch (Exception e3) {
										System.out.print("Error2 :V");
									}

								} else if (element == 3) {
									Integer id3 = legajoDao.maxIdPuntajeTipo3();
									Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
									Integer idCapituloPuntaje = 0;
									if (maxIdCapituloPuntaje == null) {
										idCapituloPuntaje = 1;
									} else {
										idCapituloPuntaje = maxIdCapituloPuntaje + 1;
									}
									try {
										legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
									} catch (Exception e4) {
										System.out.print("Error3 :V");
									}
								}

								System.out.println("Element: " + element);

							}
						}

					}
				} else {
					System.out.print("A??os detectados.");
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer a??os = form.getYears();
					Integer result = puntaje * a??os;
					System.out.println("Puntaje: " + result);

					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntaje(result);

					} catch (Exception e) {
						System.out.print("Error...3 :V");
						List<Map<String, Integer>> list = legajoDao.queryList(result);
						for (int index = 0; index < list.size(); index++) {
							Integer element = list.get(index).get("id_t_puntaje");
							if (element == 1) {
								System.out.println("Entrando a la 1.");
								Integer id1 = legajoDao.maxIdPuntajeTipo1();
								Integer idformulario = legajoDao.maxIdFormulario();
								Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
								Integer idFormularioPuntaje = 0;
								if (maxIdFormularioPuntaje == null) {
									idFormularioPuntaje = 1;
								} else {
									idFormularioPuntaje = maxIdFormularioPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
											idFactor);
								} catch (Exception e2) {
									System.out.print("Error1 :V");

								}
							} else if (element == 2) {
								Integer id2 = legajoDao.maxIdPuntajeTipo2();
								System.out.println("id2: " + id2);
								Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
								Integer idFactorPuntaje = 0;
								if (maxIdFactorPuntaje == null) {
									idFactorPuntaje = 1;
								} else {
									idFactorPuntaje = maxIdFactorPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
								} catch (Exception e3) {
									System.out.print("Error2 :V");
								}

							} else if (element == 3) {
								Integer id3 = legajoDao.maxIdPuntajeTipo3();
								Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
								Integer idCapituloPuntaje = 0;
								if (maxIdCapituloPuntaje == null) {
									idCapituloPuntaje = 1;
								} else {
									idCapituloPuntaje = maxIdCapituloPuntaje + 1;
								}
								try {
									legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
								} catch (Exception e4) {
									System.out.print("Error3 :V");
								}
							}

							System.out.println("Element: " + element);

						}
					}

				}
			} else {
				if(form.getIdItem() == 7 || form.getIdItem() == 8 || form.getIdItem() == 9 ) {
					System.out.println("Cr??ditos detectados.");
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer creditos = form.getCreditos();
					if(creditos <= 23) {
						System.out.println("Menos de 24 cr??ditos.");
						Integer result = 0;
						System.out.println("Puntaje: "+result);
						//
						Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
						Integer idPuntaje = 0;
						if (maxIdPuntaje == null) {
							idPuntaje = 1;
						} else {
							idPuntaje = maxIdPuntaje + 1;
						}
						try {
							legajoDao.queryInsertPuntajeSingle(idPuntaje, result);
						} catch (Exception e) {
							System.out.println("Error else > 0, <=23");
						}
					} else {
						Integer result = puntaje * 1;
						System.out.println("Puntaje: "+result);
						//
						Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
						Integer idPuntaje = 0;
						if (maxIdPuntaje == null) {
							idPuntaje = 1;
						} else {
							idPuntaje = maxIdPuntaje + 1;
						}
						try {
							legajoDao.queryInsertPuntajeSingle(idPuntaje, result);
						} catch (Exception e) {
							System.out.println("Error else > 0 >= 23");
						}
					}
					
					Integer idformulario = legajoDao.maxIdFormulario();
					Integer idpuntaje = legajoDao.maxIdPuntaje();
					Integer idfactor = idFactor;
					//
					Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
					Integer idFormularioPuntaje = 0;
					if (maxIdFormularioPuntaje == null) {
						idFormularioPuntaje = 1;
					} else {
						idFormularioPuntaje = maxIdFormularioPuntaje + 1;
					}
					try {
						legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje,
								idFactor);
					} catch (Exception e2) {
						System.out.print("Error1 :V");

					}
					
				} else {
					System.out.println("A??os detectados.");
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer a??os = form.getYears();
					Integer result = puntaje * a??os;
					System.out.println("Puntaje: "+result);
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {
						legajoDao.queryInsertPuntajeSingle(idPuntaje, result);
					} catch (Exception e) {
						System.out.println("Error else A??os");
						Integer idformulario = legajoDao.maxIdFormulario();
						Integer idpuntaje = legajoDao.maxIdPuntaje();
						Integer idfactor = idFactor;
						//
						Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
						Integer idFormularioPuntaje = 0;
						if (maxIdFormularioPuntaje == null) {
							idFormularioPuntaje = 1;
						} else {
							idFormularioPuntaje = maxIdFormularioPuntaje + 1;
						}
						try {
							legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje,
									idFactor);
						} catch (Exception e2) {
							System.out.print("Error1 :V");

						}
					}
				}
			}

			break;

		case 4:

			List<Map<String, Object>> queryEstudios = legajoDao.queryEstudios();

			System.out.println("Size: " + queryEstudios.size());

			if (queryEstudios.size() == 0) {
				System.out.print("Estudios detectado y vac??o.");
				if (form.getIdItem() == 14) {
					double result = 0;
					System.out.print("ID: 14 detectado.");
					Integer creditos = form.getCreditos();
					if (creditos >= 15 && creditos <= 20) {
						result = 0.5;
					} else if (creditos >= 21 && creditos <= 23) {
						result = 0.75;
					} else if (creditos >= 24) {
						result = 1;
					} else {
						result = 0;
					}
					System.out.println("Puntaje: " + result);
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntaje(result);

					} catch (Exception e) {
						System.out.print("Error4... :V");
						List<Map<String, Integer>> list = legajoDao.queryList(result);
						for (int index = 0; index < list.size(); index++) {
							Integer element = list.get(index).get("id_t_puntaje");
							if (element == 1) {
								System.out.println("Entrando a la 1.");
								Integer id1 = legajoDao.maxIdPuntajeTipo1();
								Integer idformulario = legajoDao.maxIdFormulario();
								Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
								Integer idFormularioPuntaje = 0;
								if (maxIdFormularioPuntaje == null) {
									idFormularioPuntaje = 1;
								} else {
									idFormularioPuntaje = maxIdFormularioPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
											idFactor);
								} catch (Exception e2) {
									System.out.print("Error1 :V");

								}
							} else if (element == 2) {
								Integer id2 = legajoDao.maxIdPuntajeTipo2();
								System.out.println("id2: " + id2);
								Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
								Integer idFactorPuntaje = 0;
								if (maxIdFactorPuntaje == null) {
									idFactorPuntaje = 1;
								} else {
									idFactorPuntaje = maxIdFactorPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
								} catch (Exception e3) {
									System.out.print("Error2 :V");
								}

							} else if (element == 3) {
								Integer id3 = legajoDao.maxIdPuntajeTipo3();
								Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
								Integer idCapituloPuntaje = 0;
								if (maxIdCapituloPuntaje == null) {
									idCapituloPuntaje = 1;
								} else {
									idCapituloPuntaje = maxIdCapituloPuntaje + 1;
								}
								try {
									legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
								} catch (Exception e4) {
									System.out.print("Error3 :V");
								}
							}

							System.out.println("Element: " + element);

						}
					}
				} else {
					System.out.print("A??os detectados.");
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer a??os = form.getYears();
					Integer result = puntaje * a??os;
					System.out.println("Puntaje: " + result);

					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntaje(result);
						
					} catch (Exception e) {
						System.out.print("Error4...2 :V");
						List<Map<String, Integer>> list = legajoDao.queryList(result);
						for (int index = 0; index < list.size(); index++) {
							Integer element = list.get(index).get("id_t_puntaje");
							if (element == 1) {
								System.out.println("Entrando a la 1.");
								Integer id1 = legajoDao.maxIdPuntajeTipo1();
								Integer idformulario = legajoDao.maxIdFormulario();
								Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
								Integer idFormularioPuntaje = 0;
								if (maxIdFormularioPuntaje == null) {
									idFormularioPuntaje = 1;
								} else {
									idFormularioPuntaje = maxIdFormularioPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
											idFactor);
								} catch (Exception e2) {
									System.out.print("Error1 :V");

								}
							} else if (element == 2) {
								Integer id2 = legajoDao.maxIdPuntajeTipo2();
								System.out.println("id2: " + id2);
								Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
								Integer idFactorPuntaje = 0;
								if (maxIdFactorPuntaje == null) {
									idFactorPuntaje = 1;
								} else {
									idFactorPuntaje = maxIdFactorPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
								} catch (Exception e3) {
									System.out.print("Error2 :V");
								}

							} else if (element == 3) {
								Integer id3 = legajoDao.maxIdPuntajeTipo3();
								Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
								Integer idCapituloPuntaje = 0;
								if (maxIdCapituloPuntaje == null) {
									idCapituloPuntaje = 1;
								} else {
									idCapituloPuntaje = maxIdCapituloPuntaje + 1;
								}
								try {
									legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
								} catch (Exception e4) {
									System.out.print("Error3 :V");
								}
							}

							System.out.println("Element: " + element);

						}
					}
				}
			} else {
				System.out.print("Estudios detectados.");
				if (form.getIdItem() == 14) {
					double result = 0;
					System.out.print("ID: 14 detectado.");
					Integer creditos = form.getCreditos();
					if (creditos >= 15 && creditos <= 20) {
						result = 0.5;
					} else if (creditos >= 21 && creditos <= 23) {
						result = 0.75;
					} else if (creditos >= 24) {
						result = 1;
					} else {
						result = 0;
					}
					System.out.println("Puntaje: " + result);
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntajeSingle(idPuntaje, result);
						

					} catch (Exception e) {
						System.out.print("Error4... :V");
						Integer idformulario = legajoDao.maxIdFormulario();
						Integer idpuntaje = legajoDao.maxIdPuntaje();
						Integer idfactor = idFactor;
						//
						Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
						Integer idFormularioPuntaje = 0;
						if (maxIdFormularioPuntaje == null) {
							idFormularioPuntaje = 1;
						} else {
							idFormularioPuntaje = maxIdFormularioPuntaje + 1;
						}
						try {
							legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje,
									idfactor);
						} catch (Exception e2) {
							System.out.print("Error4... :V");
						}
					}

				} else {
					System.out.print("A??os detectados.");
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer a??os = form.getYears();
					Integer result = puntaje * a??os;
					System.out.println("Puntaje: " + result);
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntajeSingle(idPuntaje, result);

					} catch (Exception e) {
						System.out.print("Error4... :V");
						Integer idformulario = legajoDao.maxIdFormulario();
						Integer idpuntaje = legajoDao.maxIdPuntaje();
						Integer idfactor = idFactor;
						
						//
						Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
						Integer idFormularioPuntaje = 0;
						if (maxIdFormularioPuntaje == null) {
							idFormularioPuntaje = 1;
						} else {
							idFormularioPuntaje = maxIdFormularioPuntaje + 1;
						}

						try {
							legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje, idfactor);
						} catch (Exception e2) {
							System.out.print("Error4... :V");
						}
					}
				}
			}

			break;

		case 6:

			List<Map<String, Object>> queryIdiomas = legajoDao.queryIdiomas();

			System.out.println("Size: " + queryIdiomas.size());

			if (queryIdiomas.size() == 0) {
				if (form.getIdItem() == 17 || form.getIdItem() == 18 || form.getIdItem() == 19 || form.getIdItem() == 20) {
					System.out.println("Hola :D");
					Integer unidad = form.getUnidad();
					System.out.println("Unidad: " + unidad);
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer result = unidad * puntaje;
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntaje(result);
						
					} catch (Exception e) {
						System.out.print("Error5... :V");
						List<Map<String, Integer>> list = legajoDao.queryList(result);
						for (int index = 0; index < list.size(); index++) {
							Integer element = list.get(index).get("id_t_puntaje");
							if (element == 1) {
								System.out.println("Entrando a la 1.");
								Integer id1 = legajoDao.maxIdPuntajeTipo1();
								Integer idformulario = legajoDao.maxIdFormulario();
								Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
								Integer idFormularioPuntaje = 0;
								if (maxIdFormularioPuntaje == null) {
									idFormularioPuntaje = 1;
								} else {
									idFormularioPuntaje = maxIdFormularioPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, id1,
											idFactor);
								} catch (Exception e2) {
									System.out.print("Error1 :V");

								}
							} else if (element == 2) {
								Integer id2 = legajoDao.maxIdPuntajeTipo2();
								System.out.println("id2: " + id2);
								Integer maxIdFactorPuntaje = legajoDao.maxIdFactorPuntaje();
								Integer idFactorPuntaje = 0;
								if (maxIdFactorPuntaje == null) {
									idFactorPuntaje = 1;
								} else {
									idFactorPuntaje = maxIdFactorPuntaje + 1;
								}
								try {
									legajoDao.queryInsertFactorPuntaje(idFactorPuntaje, idFactor, id2);
								} catch (Exception e3) {
									System.out.print("Error2 :V");
								}

							} else if (element == 3) {
								Integer id3 = legajoDao.maxIdPuntajeTipo3();
								Integer maxIdCapituloPuntaje = legajoDao.maxIdCapituloPuntaje();
								Integer idCapituloPuntaje = 0;
								if (maxIdCapituloPuntaje == null) {
									idCapituloPuntaje = 1;
								} else {
									idCapituloPuntaje = maxIdCapituloPuntaje + 1;
								}
								try {
									legajoDao.queryInsertCapituloPuntaje(idCapituloPuntaje, null, 1, id3);
								} catch (Exception e4) {
									System.out.print("Error3 :V");
								}
							}

							System.out.println("Element: " + element);

						}
					}
				}
			} else {
				if (form.getIdItem() == 17 || form.getIdItem() == 18 || form.getIdItem() == 19 || form.getIdItem() == 20) {
					System.out.println("Hola :D");
					Integer unidad = form.getUnidad();
					System.out.println("Unidad: " + unidad);
					Integer puntaje = legajoDao.queryPuntos(form.getIdItem());
					Integer result = unidad * puntaje;
					//
					Integer maxIdPuntaje = legajoDao.maxIdPuntaje();
					Integer idPuntaje = 0;
					if (maxIdPuntaje == null) {
						idPuntaje = 1;
					} else {
						idPuntaje = maxIdPuntaje + 1;
					}
					try {

						legajoDao.queryInsertPuntajeSingle(idPuntaje, result);
						
					} catch (Exception e) {
						System.out.print("Error else 5 :V");
						System.out.println("Puntaje: " + result);
						Integer idformulario = legajoDao.maxIdFormulario();
						Integer idpuntaje = legajoDao.maxIdPuntaje();
						Integer idfactor = idFactor;
						
						//
						Integer maxIdFormularioPuntaje = legajoDao.maxIdFormularioPuntaje();
						Integer idFormularioPuntaje = 0;
						if (maxIdFormularioPuntaje == null) {
							idFormularioPuntaje = 1;
						} else {
							idFormularioPuntaje = maxIdFormularioPuntaje + 1;
						}

						try {
							legajoDao.queryInsertFormularioPuntaje(idFormularioPuntaje, idformulario, idpuntaje, idfactor);
						} catch (Exception e2) {
							System.out.print("Error4... :V");
						}
					}
				}
			}

			break;

		default:
			break;
		}
	}

}

