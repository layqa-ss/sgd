package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface ProgramaService {
	
	Long saveOrUpdatePrograma(ProgramaNuevoDto programa) throws SgdServicesException;
	
	void borrarPrograma(Long id) throws SgdServicesException;
	
	List<ProgramaDto> getProgramasAll() throws SgdServicesException;
	
	ProgramaNuevoDto obtenerProgramaPorId(Long id) throws SgdServicesException;
	
	void enviarCC(Long id) throws SgdServicesException;
}
