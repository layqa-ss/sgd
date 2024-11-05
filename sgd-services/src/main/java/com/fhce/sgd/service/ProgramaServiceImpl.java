package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.programas.BibliografiaDto;
import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaIntegranteDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;
import com.fhce.sgd.model.gestion.UnidadCurricular;
import com.fhce.sgd.model.programas.Bibliografia;
import com.fhce.sgd.model.programas.MarcoAcademico;
import com.fhce.sgd.model.programas.Programa;
import com.fhce.sgd.model.programas.ProgramaIntegrante;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.repository.ProgramaRepository;
import com.fhce.sgd.repository.UnidadCurricularRepository;
import com.fhce.sgd.repository.UsuarioRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProgramaServiceImpl implements ProgramaService {

	@Autowired
	private UnidadCurricularRepository ucRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProgramaRepository programaRepository;

	@Autowired
	private GestionService gestionService;

	@Transactional(rollbackFor = Exception.class)
	public Long saveOrUpdatePrograma(ProgramaNuevoDto pDto) throws SgdServicesException {
		try {
			Programa p = new Programa();
			if (pDto.getId() != null) {
				p = programaRepository.findById(pDto.getId()).get();
			}

			// Se busca si existe unidad curricular con el nombre ingresado, si no se crea
			Iterable<UnidadCurricular> unidadesTodas = ucRepository.findAll();
			List<UnidadCurricular> unidades = StreamSupport.stream(unidadesTodas.spliterator(), false)
					.filter(u -> u.getNombreUC().equals(pDto.getNombreUC())).toList();
			if (unidades.isEmpty()) {
				UnidadCurricular uc = new UnidadCurricular();
				uc.setNombreUC(pDto.getNombreUC());
				uc = ucRepository.save(uc);
				p.setUc(uc);
			} else {
				p.setUc(unidades.get(0));
			}

			MarcoAcademico ma = new MarcoAcademico();
			if (p.getMa() != null) {
				ma = p.getMa();
			}

			Set<UnidadAcademica> unidadesAcademicas = new HashSet<UnidadAcademica>();
			for (UnidadAcademicaDto uaDto : pDto.getUnidades()) {
				UnidadAcademica ua = gestionService.getUnidadAcademica(uaDto.getId());
				unidadesAcademicas.add(ua);
			}
			ma.setUnidadesAcademicas(unidadesAcademicas);

			Set<Carrera> carreras = new HashSet<Carrera>();
			Set<AreaTematica> areasTematicas = new HashSet<AreaTematica>();

			for (CarreraDto cDto : pDto.getCarreras()) {
				Carrera c = gestionService.getCarrera(cDto.getId());
				carreras.add(c);

				AreaTematica a = gestionService.getAreaTematica(cDto.getArea());
				areasTematicas.add(a);
			}
			ma.setCarreras(carreras);
			ma.setAreasTematicas(areasTematicas);

			ma.setPrograma(p);
			p.setMa(ma);

			p.setDuracion(pDto.getDuracion());
			p.setDuracionOtro(pDto.getDuracionOtro());
			p.setSemestre(pDto.getSemestre());
			p.setIngreso(pDto.isIngreso());
			p.setRequisitos(pDto.isRequisitos());
			p.setRequisitosCuales(pDto.getRequisitosCuales());
			p.setRecomendaciones(pDto.getRecomendaciones());
			p.setRecomNoCorresponde(pDto.isRecomNoCorresponde());
			p.setOtrosServicios(pDto.isOtrosServicios());

			p.getIntegrantes().clear();
			Set<ProgramaIntegrante> integrantes = new HashSet<>();
			for (ProgramaIntegranteDto iDto : pDto.getIntegrantes()) {
				ProgramaIntegrante i = new ProgramaIntegrante(iDto.getRol(), iDto.getCargo(), iDto.getCargoOtro(),
						iDto.getUnidad_academica(), iDto.getSubunidad_academica());
				Iterable<Usuario> usuariosTodos = usuarioRepository.findAll();
				List<Usuario> usuarios = StreamSupport.stream(usuariosTodos.spliterator(), false)
						.filter(u -> u.getId() == iDto.getIdUsuario()).toList();
				if (!usuarios.isEmpty()) {
					i.setDocente(usuarios.get(0));
				}
				i.setPrograma(p);
				integrantes.add(i);
			}
			p.getIntegrantes().addAll(integrantes);

			p.setHorasAula(pDto.getHorasAula());
			p.setHorasTrabajosConAcompa(pDto.getHorasTrabajosConAcompa());
			p.setOtrosConAcompa(pDto.getOtrosConAcompa());
			p.setHorasOtrosConAcompa(pDto.getHorasOtrosConAcompa());

			p.setHorasLecturas(pDto.getHorasLecturas());
			p.setHorasTareas(pDto.getHorasTareas());
			p.setHorasTrabajosSinAcompa(pDto.getHorasTrabajosSinAcompa());
			p.setHorasTrabajosFinales(pDto.getHorasTrabajosFinales());
			p.setOtrosSinAcompa(pDto.getOtrosSinAcompa());
			p.setHorasOtrosSinAcompa(pDto.getHorasOtrosSinAcompa());

			p.setHorasTotales(pDto.getHorasTotales());
			p.setCreditos(pDto.getCreditos());

			p.setFormato(pDto.getFormato());
			p.setRegimen(pDto.getRegimen());
			p.setModalidad(pDto.getModalidad());
			p.setAdecuaciones(pDto.isAdecuaciones());
			p.setDescripcionAdecuaciones(pDto.getDescripcionAdecuaciones());
			p.setObjetivos(pDto.getObjetivos());
			p.setContenidos(pDto.getContenidos());
			p.setDescrMetodologia(pDto.getDescrMetodologia());
			p.setTareas75obligatoria(pDto.isTareas75obligatoria());
			p.setAprobDirecta(pDto.isAprobDirecta());
			p.setDescrEvaluacion(pDto.getDescrEvaluacion());

			p.getBibliografia().clear();
			Set<Bibliografia> biblio = new HashSet<>();
			for (BibliografiaDto b : pDto.getBibliografia()) {
				Bibliografia bib = new Bibliografia(b.getTitulo());
				bib.setOrden(b.getOrden());
				bib.setEsTitulo(b.isEsTitulo());
				bib.setPrograma(p);
				biblio.add(bib);
			}
			p.getBibliografia().addAll(biblio);

			p.setEstado(EnumEstadoPrograma.CREADO);
			p.setYear(obtenerAnioCorriente());

			p = programaRepository.save(p);
			return p.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdatePrograma de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdatePrograma de ProgramaService: " + e.getMessage(), e);
		}
	}

	private Integer obtenerAnioCorriente() throws SgdServicesException {
		try {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			return Integer.valueOf(year);
		} catch (Exception e) {
			log.error("Error en obtenerAnioCorriente de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerAnioCorriente de ProgramaService: " + e.getMessage(), e);
		}

	}

	public void borrarPrograma(Long id) throws SgdServicesException {
		try {
			programaRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Error en borrarPrograma de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en borrarPrograma de ProgramaService: " + e.getMessage(), e);
		}

	}

	public void cambiarEstado(Long id, EnumEstadoPrograma estadoNuevo) throws SgdServicesException {
		try {
			Programa prog = programaRepository.findById(id).orElse(null);
			if (prog != null) {
				prog.setEstado(estadoNuevo);
				programaRepository.save(prog);
			}
		} catch (Exception e) {
			log.error("Error en cambiarEstado de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en cambiarEstado de ProgramaService: " + e.getMessage(), e);
		}

	}

	public List<ProgramaDto> getProgramasAll() throws SgdServicesException {
		try {
			List<ProgramaDto> programasDto = new ArrayList<ProgramaDto>();
			Iterable<Programa> programasTodas = programaRepository.findAll();
			List<Programa> programas = StreamSupport.stream(programasTodas.spliterator(), false).toList();
			for (Programa p : programas) {
				ProgramaDto prog = new ProgramaDto(p.getId(), p.getUc().getNombreUC(), p.getYear(), p.getEstado());
				programasDto.add(prog);
			}
			return programasDto;
		} catch (Exception e) {
			log.error("Error en getProgramasAll de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en getProgramasAll de ProgramaService: " + e.getMessage(), e);
		}

	}

	public Programa obtenerProgramaPorId(Long id) throws SgdServicesException {
		try {
			Programa prog = programaRepository.findById(id).orElse(null);
			return prog;
		} catch (Exception e) {
			log.error("Error en obtenerProgramaPorId de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerProgramaPorId de ProgramaService: " + e.getMessage(), e);
		}
	}

	public ProgramaNuevoDto obtenerProgramaDtoPorId(Long id) throws SgdServicesException {
		try {
			Programa prog = programaRepository.findById(id).orElse(null);
			if (prog != null) {
				ProgramaNuevoDto p = new ProgramaNuevoDto();
				p.setId(id);
				p.setNombreUC(prog.getUc().getNombreUC());
				p.setDuracion(prog.getDuracion());
				p.setDuracionOtro(prog.getDuracionOtro());
				p.setSemestre(prog.getSemestre());
				p.setIngreso(prog.isIngreso());
				p.setRequisitos(prog.isRequisitos());
				p.setRequisitosCuales(prog.getRequisitosCuales());
				p.setRecomendaciones(prog.getRecomendaciones());
				p.setRecomNoCorresponde(prog.isRecomNoCorresponde());
				p.setOtrosServicios(prog.isOtrosServicios());

				p.setHorasAula(prog.getHorasAula());
				p.setHorasTrabajosConAcompa(prog.getHorasTrabajosConAcompa());
				p.setOtrosConAcompa(prog.getOtrosConAcompa());
				p.setHorasOtrosConAcompa(prog.getHorasOtrosConAcompa());
				p.setHorasLecturas(prog.getHorasLecturas());
				p.setHorasTareas(prog.getHorasTareas());
				p.setHorasTrabajosSinAcompa(prog.getHorasTrabajosSinAcompa());
				p.setHorasTrabajosFinales(prog.getHorasTrabajosFinales());
				p.setOtrosSinAcompa(prog.getOtrosSinAcompa());
				p.setHorasOtrosSinAcompa(prog.getHorasOtrosSinAcompa());
				p.setHorasTotales(prog.getHorasTotales());
				p.setCreditos(prog.getCreditos());

				p.setFormato(prog.getFormato());
				p.setRegimen(prog.getRegimen());
				p.setModalidad(prog.getModalidad());
				p.setAdecuaciones(prog.isAdecuaciones());
				p.setDescripcionAdecuaciones(prog.getDescripcionAdecuaciones());
				p.setObjetivos(prog.getObjetivos());
				p.setContenidos(prog.getContenidos());
				p.setDescrMetodologia(prog.getDescrMetodologia());
				p.setTareas75obligatoria(prog.isTareas75obligatoria());
				p.setAprobDirecta(prog.isAprobDirecta());
				p.setDescrEvaluacion(prog.getDescrEvaluacion());

				List<BibliografiaDto> biblio = new ArrayList<>();
				for (Bibliografia b : prog.getBibliografia()) {
					BibliografiaDto bDto = new BibliografiaDto(b.getId(), b.getOrden(), b.getTitulo(), b.isEsTitulo());
					biblio.add(bDto);
				}
				Collections.sort(biblio, Comparator.comparing(BibliografiaDto::getOrden));
				p.setBibliografia(biblio);

				List<ProgramaIntegranteDto> integrantes = new ArrayList<>();
				for (ProgramaIntegrante i : prog.getIntegrantes()) {
					ProgramaIntegranteDto iDto = new ProgramaIntegranteDto(i.getId(), i.getRol(), i.getCargo(),
							i.getCargoOtro(), i.getUnidad_academica(), i.getSubunidad_academica(),
							i.getDocente().getId(), i.getDocente().getFullname());
					integrantes.add(iDto);
				}
				p.setIntegrantes(integrantes);

				List<UnidadAcademicaDto> unidadesAcademicas = new ArrayList<>();
				for (UnidadAcademica ua : prog.getMa().getUnidadesAcademicas()) {
					UnidadAcademicaDto uaDto = new UnidadAcademicaDto(ua.getId(), ua.getNombreUA());
					unidadesAcademicas.add(uaDto);
				}
				p.setUnidades(unidadesAcademicas);

				List<CarreraDto> carreras = new ArrayList<>();
				for (Carrera c : prog.getMa().getCarreras()) {
					CarreraDto cDto = new CarreraDto(c.getId(), c.getNombreCarrera(), c.getUa().getId(),
							c.getUa().getNombreUA());
					for (AreaTematica a : prog.getMa().getAreasTematicas()) {
						if (a.getCarrera().getId() == c.getId()) {
							cDto.setArea(a.getId());
							cDto.setAreaNombre(a.getNombreArea());
						}
					}
					carreras.add(cDto);
				}
				p.setCarreras(carreras);

				p.setYear(prog.getYear());
				p.setEstado(prog.getEstado());
				return p;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en obtenerProgramaDtoPorId de ProgramaService: " + e.getMessage());
			throw new SgdServicesException("Error en obtenerProgramaDtoPorId de ProgramaService: " + e.getMessage(), e);
		}

	}
}
