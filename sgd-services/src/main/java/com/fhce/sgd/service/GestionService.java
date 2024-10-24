package com.fhce.sgd.service;

import java.util.List;
import java.util.Map;

import com.fhce.sgd.dto.gestion.AreaTematicaDto;
import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.RolDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.dto.gestion.UnidadCurricularDto;
import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface GestionService {

	List<AreaTematicaDto> getAreasTematicasForCarrera(Long carreraId) throws SgdServicesException;
	
	Map<Long, List<AreaTematicaDto>> getAreasPorCarrera() throws SgdServicesException;
	
	List<AreaTematicaDto> getAreasTematicas() throws SgdServicesException;

	Long addAreaTematica(AreaTematicaDto areaDto) throws SgdServicesException;
	
	void deleteAreaTematica(Long id) throws SgdServicesException;
	
	AreaTematica getAreaTematica(Long id) throws SgdServicesException;
	
	List<CarreraDto> getCarreras() throws SgdServicesException;
	
	Long addCarrera(CarreraDto carreraDto) throws SgdServicesException;
	
	void deleteCarrera(Long id) throws SgdServicesException;
	
	Carrera getCarrera(Long id) throws SgdServicesException;
	
	List<UnidadAcademicaDto> getUnidadesAcademicas() throws SgdServicesException;
	
	Long addUA(UnidadAcademicaDto uaDto) throws SgdServicesException;
	
	void deleteUA(Long id) throws SgdServicesException;
	
	UnidadAcademica getUnidadAcademica(Long id) throws SgdServicesException;
	
	List<UnidadCurricularDto> getUnidadesCurriculares() throws SgdServicesException;
	
	Long addRol(RolDto rolDto) throws SgdServicesException;
	
	void deleteRol(Long id) throws SgdServicesException;
	
	RolDto getRol(Long id) throws SgdServicesException;
	
	List<RolDto> getRoles() throws SgdServicesException;
}
