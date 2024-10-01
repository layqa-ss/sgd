package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;

public interface ProgramaService {
	
	Long saveOrUpdatePrograma(ProgramaNuevoDto programa);
	
	void borrarPrograma(Long id);
	
	List<ProgramaDto> getProgramasAll();
	
	ProgramaNuevoDto obtenerProgramaPorId(Long id);
}
