package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.programas.AccionDto;
import com.fhce.sgd.dto.programas.AprobarDto;
import com.fhce.sgd.dto.programas.RevisionDto;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface AccionService {
	
	Long saveOrUpdateRevision(RevisionDto revision) throws SgdServicesException;
	
	List<RevisionDto> getRevisionesPrograma(Long idPrograma) throws SgdServicesException;
	
	List<RevisionDto> getRevisionesProgramaAbrev(Long idPrograma) throws SgdServicesException;
	
	List<AccionDto> getAccionesPrograma(Long idPrograma) throws SgdServicesException;
	
	boolean aprobacionBedelias(Long idPrograma) throws SgdServicesException;
	
	RevisionDto obtenerRevisionPorId(Long id) throws SgdServicesException;
	
	Long saveOrUpdateAprobacion(AprobarDto aDto) throws SgdServicesException;
}
