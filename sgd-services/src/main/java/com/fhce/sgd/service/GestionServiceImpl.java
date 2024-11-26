package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.RolDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;
import com.fhce.sgd.model.gestion.UnidadCurricular;
import com.fhce.sgd.model.usuarios.Rol;
import com.fhce.sgd.repository.AreaTematicaRepository;
import com.fhce.sgd.repository.CarreraRepository;
import com.fhce.sgd.repository.RolRepository;
import com.fhce.sgd.repository.UnidadAcademicaRepository;
import com.fhce.sgd.repository.UnidadCurricularRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class GestionServiceImpl implements GestionService {

	@Autowired
	private AreaTematicaRepository areaTematicaRepository;

	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	private UnidadAcademicaRepository uaRepository;

	@Autowired
	private UnidadCurricularRepository ucRepository;

	@Autowired
	private RolRepository rolRepository;

	public List<AreaTematicaDto> getAreasTematicasForCarrera(Long carreraId) throws SgdServicesException {
		try {
			Iterable<AreaTematica> areasTodas = areaTematicaRepository.findAll();
			List<AreaTematica> areas = StreamSupport.stream(areasTodas.spliterator(), false)
					.filter(a -> a.getCarrera().getId().equals(carreraId) && a.isHabilitada()).toList();
			if (!areas.isEmpty()) {
				List<AreaTematicaDto> areasDto = new ArrayList<AreaTematicaDto>();
				for (AreaTematica a : areas) {
					AreaTematicaDto areaDto = new AreaTematicaDto(a.getId(), a.getNombreArea(), carreraId,
							a.isHabilitada());
					areasDto.add(areaDto);
				}
				return areasDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getAreasTematicasForCarrera de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAreasTematicasForCarrera de GestionService: " + e.getMessage(),
					e);
		}

	}

	public Map<Long, List<AreaTematicaDto>> getAreasPorCarrera() throws SgdServicesException {
		try {
			Map<Long, List<AreaTematicaDto>> map = new HashMap<>();
			List<CarreraDto> carreras = getCarrerasHabilitadas();
			if (carreras != null) {
				for (CarreraDto c : carreras) {
					map.put(c.getId(), getAreasTematicasForCarrera(c.getId()));
				}
			}
			return map;
		} catch (Exception e) {
			log.error("Error en getAreasPorCarrera de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAreasPorCarrera de GestionService: " + e.getMessage(), e);
		}

	}

	public List<AreaTematicaDto> getAreasTematicas() throws SgdServicesException {
		try {
			Iterable<AreaTematica> areasTodas = areaTematicaRepository.findAll();
			List<AreaTematica> areas = StreamSupport.stream(areasTodas.spliterator(), false).toList();
			if (!areas.isEmpty()) {
				List<AreaTematicaDto> areasDto = new ArrayList<AreaTematicaDto>();
				for (AreaTematica a : areas) {
					AreaTematicaDto areaDto = new AreaTematicaDto(a.getId(), a.getNombreArea(), a.getCarrera().getId(),
							a.isHabilitada());
					areaDto.setNombreCarrera(a.getCarrera().getNombreCarrera());
					areasDto.add(areaDto);
				}
				return areasDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getAreasTematicas de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAreasTematicas de GestionService: " + e.getMessage(), e);
		}
	}

	public Long addAreaTematica(AreaTematicaDto areaDto) throws SgdServicesException {
		try {
			AreaTematica area = new AreaTematica();
			area.setNombreArea(areaDto.getNombre());
			Optional<Carrera> carrera = carreraRepository.findById(areaDto.getCarreraId());
			area.setCarrera(carrera.get());
			area.setHabilitada(true);
			area = areaTematicaRepository.save(area);
			return area.getId();
		} catch (Exception e) {
			log.error("Error en addAreaTematica de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en addAreaTematica de GestionService: " + e.getMessage(), e);
		}
	}

	public void deleteAreaTematica(Long id, boolean habilitada) throws SgdServicesException {
		try {
			AreaTematica a = getAreaTematica(id);
			a.setHabilitada(habilitada);
			areaTematicaRepository.save(a);
		} catch (Exception e) {
			log.error("Error en deleteAreaTematica de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteAreaTematica de GestionService: " + e.getMessage(), e);
		}
	}

	public AreaTematica getAreaTematica(Long id) throws SgdServicesException {
		try {
			return areaTematicaRepository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error("Error en getAreaTematica de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAreaTematica de GestionService: " + e.getMessage(), e);
		}
	}
	
	public AreaTematicaDto getAreaTematicaDto(Long id) throws SgdServicesException {
		try {
			AreaTematica a = areaTematicaRepository.findById(id).orElse(null);
			if (a != null) {
				return new AreaTematicaDto(a.getId(), a.getNombreArea(), a.getCarrera().getId(), a.isHabilitada());
			}
			return null;
		} catch (Exception e) {
			log.error("Error en getAreaTematicaDto de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getAreaTematicaDto de GestionService: " + e.getMessage(), e);
		}
	}
	
	public List<CarreraDto> getCarrerasHabilitadas() throws SgdServicesException {
		try {
			Iterable<Carrera> carrerasTodas = carreraRepository.findAll();
			List<Carrera> carreras = StreamSupport.stream(carrerasTodas.spliterator(), false).filter(c -> c.isHabilitada()).toList();
			if (!carreras.isEmpty()) {
				List<CarreraDto> carrerasDto = new ArrayList<CarreraDto>();
				for (Carrera c : carreras) {
					CarreraDto carreraDto = new CarreraDto(c.getId(), c.getNombreCarrera(), c.getUa().getId(),
							c.getUa().getNombreUA(), c.isHabilitada());
					carreraDto.setNombreUA(c.getUa().getNombreUA());
					carrerasDto.add(carreraDto);
				}
				return carrerasDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getCarreras de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getCarreras de GestionService: " + e.getMessage(), e);
		}
	}

	public List<CarreraDto> getCarreras() throws SgdServicesException {
		try {
			Iterable<Carrera> carrerasTodas = carreraRepository.findAll();
			List<Carrera> carreras = StreamSupport.stream(carrerasTodas.spliterator(), false).toList();
			if (!carreras.isEmpty()) {
				List<CarreraDto> carrerasDto = new ArrayList<CarreraDto>();
				for (Carrera c : carreras) {
					CarreraDto carreraDto = new CarreraDto(c.getId(), c.getNombreCarrera(), c.getUa().getId(),
							c.getUa().getNombreUA(), c.isHabilitada());
					carreraDto.setNombreUA(c.getUa().getNombreUA());
					carrerasDto.add(carreraDto);
				}
				return carrerasDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getCarreras de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getCarreras de GestionService: " + e.getMessage(), e);
		}
	}

	public Long addCarrera(CarreraDto carreraDto) throws SgdServicesException {
		try {
			Carrera carrera = new Carrera();
			carrera.setNombreCarrera(carreraDto.getNombre());
			Optional<UnidadAcademica> ua = uaRepository.findById(carreraDto.getUaId());
			carrera.setUa(ua.get());
			carrera.setHabilitada(true);
			carrera = carreraRepository.save(carrera);
			return carrera.getId();
		} catch (Exception e) {
			log.error("Error en addCarrera de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en addCarrera de GestionService: " + e.getMessage(), e);
		}
	}

	public void deleteCarrera(Long id, boolean habilitada) throws SgdServicesException {
		try {
			Carrera c = getCarrera(id);
			c.setHabilitada(habilitada);
			carreraRepository.save(c);
		} catch (Exception e) {
			log.error("Error en deleteCarrera de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteCarrera de GestionService: " + e.getMessage(), e);
		}
	}

	public Carrera getCarrera(Long id) throws SgdServicesException {
		try {
			return carreraRepository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error("Error en getCarrera de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getCarrera de GestionService: " + e.getMessage(), e);
		}
	}

	public List<UnidadAcademicaDto> getUnidadesAcademicas() throws SgdServicesException {
		try {
			Iterable<UnidadAcademica> unidadesTodas = uaRepository.findAll();
			List<UnidadAcademica> unidades = StreamSupport.stream(unidadesTodas.spliterator(), false).toList();
			if (!unidades.isEmpty()) {
				List<UnidadAcademicaDto> unidadesDto = new ArrayList<UnidadAcademicaDto>();
				for (UnidadAcademica ua : unidadesTodas) {
					UnidadAcademicaDto uaDto = new UnidadAcademicaDto(ua.getId(), ua.getNombreUA(), ua.isHabilitada());
					unidadesDto.add(uaDto);
				}
				return unidadesDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUnidadesAcademicas de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getUnidadesAcademicas de GestionService: " + e.getMessage(), e);
		}

	}
	
	public List<UnidadAcademicaDto> getUnidadesAcademicasHabilitadas() throws SgdServicesException {
		try {
			Iterable<UnidadAcademica> unidadesTodas = uaRepository.findAll();
			List<UnidadAcademica> unidades = StreamSupport.stream(unidadesTodas.spliterator(), false).filter(ua -> ua.isHabilitada()).toList();
			if (!unidades.isEmpty()) {
				List<UnidadAcademicaDto> unidadesDto = new ArrayList<UnidadAcademicaDto>();
				for (UnidadAcademica ua : unidades) {
					UnidadAcademicaDto uaDto = new UnidadAcademicaDto(ua.getId(), ua.getNombreUA(), ua.isHabilitada());
					unidadesDto.add(uaDto);
				}
				return unidadesDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUnidadesAcademicas de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getUnidadesAcademicas de GestionService: " + e.getMessage(), e);
		}

	}

	public Long addUA(UnidadAcademicaDto uaDto) throws SgdServicesException {
		try {
			UnidadAcademica ua = new UnidadAcademica();
			ua.setNombreUA(uaDto.getNombre());
			ua.setHabilitada(true);
			ua = uaRepository.save(ua);
			return ua.getId();
		} catch (Exception e) {
			log.error("Error en addUA de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en addUA de GestionService: " + e.getMessage(), e);
		}

	}

	public void deleteUA(Long id, boolean habilitada) throws SgdServicesException {
		try {
			UnidadAcademica ua = getUnidadAcademica(id);
			ua.setHabilitada(habilitada);
			uaRepository.save(ua);
		} catch (Exception e) {
			log.error("Error en deleteUA de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteUA de GestionService: " + e.getMessage(), e);
		}

	}

	public UnidadAcademica getUnidadAcademica(Long id) throws SgdServicesException {
		try {
			return uaRepository.findById(id).orElse(null);
		} catch (Exception e) {
			log.error("Error en getUnidadAcademica de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getUnidadAcademica de GestionService: " + e.getMessage(), e);
		}

	}

	public List<UnidadCurricularDto> getUnidadesCurriculares() throws SgdServicesException {
		try {
			Iterable<UnidadCurricular> unidadesTodas = ucRepository.findAll();
			List<UnidadCurricular> unidades = StreamSupport.stream(unidadesTodas.spliterator(), false).toList();
			if (!unidades.isEmpty()) {
				List<UnidadCurricularDto> unidadesDto = new ArrayList<UnidadCurricularDto>();
				for (UnidadCurricular uc : unidadesTodas) {
					UnidadCurricularDto ucDto = new UnidadCurricularDto(uc.getId(), uc.getNombreUC());
					unidadesDto.add(ucDto);
				}
				return unidadesDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUnidadesCurriculares de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getUnidadesCurriculares de GestionService: " + e.getMessage(), e);
		}

	}

	public List<RolDto> getRoles() throws SgdServicesException {
		try {
			Iterable<Rol> rolesTodos = rolRepository.findAll();
			List<Rol> roles = StreamSupport.stream(rolesTodos.spliterator(), false).toList();
			if (!roles.isEmpty()) {
				List<RolDto> rolDto = new ArrayList<RolDto>();
				for (Rol r : rolesTodos) {
					RolDto rDto = new RolDto(r.getId(), r.getNombre(), r.getOperaciones());
					rolDto.add(rDto);
				}
				return rolDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getRoles de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getRoles de GestionService: " + e.getMessage(), e);
		}

	}

	public Long addRol(RolDto rolDto) throws SgdServicesException {
		try {
			Rol r = new Rol();
			if (rolDto.getId() != null) {
				r = rolRepository.findById(rolDto.getId()).get();
			}
			r.setNombre(rolDto.getNombre());
			r.setOperaciones(rolDto.getOperaciones());
			r = rolRepository.save(r);
			return r.getId();
		} catch (Exception e) {
			log.error("Error en addRol de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en addRol de GestionService: " + e.getMessage(), e);
		}

	}

	public void deleteRol(Long id) throws SgdServicesException {
		try {
			rolRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Error en deleteRol de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteRol de GestionService: " + e.getMessage(), e);
		}

	}

	public RolDto getRol(Long id) throws SgdServicesException {
		try {
			Rol r = rolRepository.findById(id).orElse(null);
			if (r != null) {
				RolDto rd = new RolDto(r.getId(), r.getNombre(), r.getOperaciones());
				return rd;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getRol de GestionService: " + e.getMessage());
			throw new SgdServicesException("Error en getRol de GestionService: " + e.getMessage(), e);
		}

	}
}
