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
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;
import com.fhce.sgd.model.gestion.UnidadCurricular;
import com.fhce.sgd.repository.AreaTematicaRepository;
import com.fhce.sgd.repository.CarreraRepository;
import com.fhce.sgd.repository.UnidadAcademicaRepository;
import com.fhce.sgd.repository.UnidadCurricularRepository;

@Service
@Transactional
public class GestionServiceImpl implements GestionService {

    @Autowired
    private AreaTematicaRepository areaTematicaRepository;
    
    @Autowired
    private CarreraRepository carreraRepository;
    
    @Autowired
    private UnidadAcademicaRepository uaRepository;
    
    @Autowired
    private UnidadCurricularRepository ucRepository;

    public List<AreaTematicaDto> getAreasTematicasForCarrera(Long carreraId) {
    	Iterable<AreaTematica> areasTodas = areaTematicaRepository.findAll();
    	List<AreaTematica> areas = StreamSupport.stream(areasTodas.spliterator(), false).filter(
    			a -> a.getCarrera().getId().equals(carreraId)).toList();
        if (!areas.isEmpty()) {
        	List<AreaTematicaDto> areasDto = new ArrayList<AreaTematicaDto>();
        	for (AreaTematica a : areas) {
        		AreaTematicaDto areaDto = new AreaTematicaDto(a.getId(), a.getNombreArea(), carreraId);
        		areasDto.add(areaDto);
        	}
        	return areasDto;
        } else {
            return null;
        }
    }
    
    public Map<Long, List<AreaTematicaDto>> getAreasPorCarrera() {
    	Map<Long, List<AreaTematicaDto>> map = new HashMap<>();
    	List<CarreraDto> carreras = getCarreras();
    	if (carreras != null) {
    		for (CarreraDto c : carreras) {
        		map.put(c.getId(), getAreasTematicasForCarrera(c.getId()));
        	}
    	}
    	return map;
    }
    
    public List<AreaTematicaDto> getAreasTematicas() {
    	Iterable<AreaTematica> areasTodas = areaTematicaRepository.findAll();
    	List<AreaTematica> areas = StreamSupport.stream(areasTodas.spliterator(), false).toList();
        if (!areas.isEmpty()) {
        	List<AreaTematicaDto> areasDto = new ArrayList<AreaTematicaDto>();
        	for (AreaTematica a : areas) {
        		AreaTematicaDto areaDto = new AreaTematicaDto(a.getId(), a.getNombreArea(), a.getCarrera().getId());
        		areaDto.setNombreCarrera(a.getCarrera().getNombreCarrera());
        		areasDto.add(areaDto);
        	}
        	return areasDto;
        } else {
            return null;
        }
    }
    
    public Long addAreaTematica(AreaTematicaDto areaDto) {
    	AreaTematica area = new AreaTematica();
    	area.setNombreArea(areaDto.getNombre());
    	Optional<Carrera> carrera = carreraRepository.findById(areaDto.getCarreraId());
    	area.setCarrera(carrera.get());
    	area = areaTematicaRepository.save(area);
        return area.getId();
    }
    
    public void deleteAreaTematica(Long id) {
    	areaTematicaRepository.deleteById(id);
    }
    
    public AreaTematica getAreaTematica(Long id) {
    	return areaTematicaRepository.findById(id).orElse(null);
    }
    
    public List<CarreraDto> getCarreras() {
    	Iterable<Carrera> carrerasTodas = carreraRepository.findAll();
    	List<Carrera> carreras = StreamSupport.stream(carrerasTodas.spliterator(), false).toList();
        if (!carreras.isEmpty()) {
        	List<CarreraDto> carrerasDto = new ArrayList<CarreraDto>();
        	for (Carrera c : carrerasTodas) {
        		CarreraDto carreraDto = new CarreraDto(c.getId(), c.getNombreCarrera(), c.getUa().getId(), c.getUa().getNombreUA());
        		carreraDto.setNombreUA(c.getUa().getNombreUA());
        		carrerasDto.add(carreraDto);
        	}
        	return carrerasDto;
        } else {
            return null;
        }
    }
    
    public Long addCarrera(CarreraDto carreraDto) {
    	Carrera carrera = new Carrera();
    	carrera.setNombreCarrera(carreraDto.getNombre());
    	Optional<UnidadAcademica> ua = uaRepository.findById(carreraDto.getUaId());
    	carrera.setUa(ua.get());
    	carrera = carreraRepository.save(carrera);
        return carrera.getId();
    }
    
    public void deleteCarrera(Long id) {
    	carreraRepository.deleteById(id);
    }
    
    public Carrera getCarrera(Long id) {
    	return carreraRepository.findById(id).orElse(null);
    }
    
    public List<UnidadAcademicaDto> getUnidadesAcademicas() {
    	Iterable<UnidadAcademica> unidadesTodas = uaRepository.findAll();
    	List<UnidadAcademica> unidades = StreamSupport.stream(unidadesTodas.spliterator(), false).toList();
        if (!unidades.isEmpty()) {
        	List<UnidadAcademicaDto> unidadesDto = new ArrayList<UnidadAcademicaDto>();
        	for (UnidadAcademica ua : unidadesTodas) {
        		UnidadAcademicaDto uaDto = new UnidadAcademicaDto(ua.getId(), ua.getNombreUA());
        		unidadesDto.add(uaDto);
        	}
        	return unidadesDto;
        } else {
            return null;
        }
    }
    
    public Long addUA(UnidadAcademicaDto uaDto) {
    	UnidadAcademica ua = new UnidadAcademica();
    	ua.setNombreUA(uaDto.getNombre());
    	ua = uaRepository.save(ua);
        return ua.getId();
    }
    
    public void deleteUA(Long id) {
    	uaRepository.deleteById(id);
    }
    
    public UnidadAcademica getUnidadAcademica(Long id) {
    	return uaRepository.findById(id).orElse(null);
    }
    
    public List<UnidadCurricularDto> getUnidadesCurriculares() {
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
    }
}
