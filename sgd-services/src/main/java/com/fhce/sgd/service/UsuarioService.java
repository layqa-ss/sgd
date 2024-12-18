package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface UsuarioService {

	UsuarioDto getUsuarioDto(Long id) throws SgdServicesException;
	
	Usuario getUsuario(Long id) throws SgdServicesException;
	
	Usuario getUsuarioByUsername(String username) throws SgdServicesException;
	
	Long saveOrUpdateUsuario(UsuarioDto userDto) throws SgdServicesException;
	
	List<UsuarioDto> getUsuarios() throws SgdServicesException;
	
	void deleteUsuario(Long id) throws SgdServicesException;
	
	List<Long> obtenerCarrerasUsuario(Long id) throws SgdServicesException;
	
	List<Long> obtenerUnidadesUsuario(Long id) throws SgdServicesException;
}
