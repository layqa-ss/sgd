package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.programas.ProgramaDto;
import com.fhce.sgd.dto.programas.ProgramaNuevoDto;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.programas.Programa;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface ProgramaService {
	
	Long saveOrUpdatePrograma(ProgramaNuevoDto programa) throws SgdServicesException;
	
	void borrarPrograma(Long id) throws SgdServicesException;
	
	List<ProgramaDto> getProgramasAll() throws SgdServicesException;
	
	List<ProgramaDto> getProgramasEnProceso() throws SgdServicesException;
	
	List<ProgramaDto> getProgramasAprobados() throws SgdServicesException;
	
	ProgramaNuevoDto obtenerProgramaDtoPorId(Long id) throws SgdServicesException;
	
	Programa obtenerProgramaPorId(Long id) throws SgdServicesException;
	
	void cambiarEstado(Long id, EnumEstadoPrograma estadoNuevo) throws SgdServicesException;
}
