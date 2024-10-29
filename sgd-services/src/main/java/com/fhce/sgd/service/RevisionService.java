package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.programas.RevisionDto;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface RevisionService {
	
	Long saveOrUpdateRevision(RevisionDto revision) throws SgdServicesException;
	
	List<RevisionDto> getRevisionesPrograma(Long idPrograma) throws SgdServicesException;
	
	RevisionDto obtenerRevisionPorId(Long id) throws SgdServicesException;
}
