package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.gestion.UsuarioDto;

public interface UsuarioService {

	UsuarioDto getUsuario(Long id);
	
	UsuarioDto getUsuarioByUsername(String username);
	
	Long save(UsuarioDto userDto);
	
	List<UsuarioDto> getUsuarios();
	
	void deleteUsuario(Long id);
}
