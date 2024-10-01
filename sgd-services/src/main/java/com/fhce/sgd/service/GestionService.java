package com.fhce.sgd.service;

import java.util.List;
import java.util.Map;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;

public interface GestionService {

	List<AreaTematicaDto> getAreasTematicasForCarrera(Long carreraId);
	
	Map<Long, List<AreaTematicaDto>> getAreasPorCarrera();
	
	List<AreaTematicaDto> getAreasTematicas();

	Long addAreaTematica(AreaTematicaDto areaDto);
	
	void deleteAreaTematica(Long id);
	
	AreaTematica getAreaTematica(Long id);
	
	List<CarreraDto> getCarreras();
	
	Long addCarrera(CarreraDto carreraDto);
	
	void deleteCarrera(Long id);
	
	Carrera getCarrera(Long id);
	
	List<UnidadAcademicaDto> getUnidadesAcademicas();
	
	Long addUA(UnidadAcademicaDto uaDto);
	
	void deleteUA(Long id);
	
	UnidadAcademica getUnidadAcademica(Long id);
	
	List<UnidadCurricularDto> getUnidadesCurriculares();
}
