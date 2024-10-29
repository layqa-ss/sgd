package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.usuarios.Rol;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.repository.RolRepository;
import com.fhce.sgd.repository.UsuarioRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepo;

	public UsuarioDto getUsuarioDto(Long id) throws SgdServicesException {
		try {
			Optional<Usuario> u = usuarioRepository.findById(id);
			if (u.isPresent()) {
				return new UsuarioDto(u.get().getId(), u.get().getUsername(), u.get().getPassword(),
						u.get().getCreationDate(), u.get().getFullname(), u.get().getRol().getId());
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuarioDto de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuarioDto de UsuarioService: " + e.getMessage() , e);
		}
		
	}
	
	public Usuario getUsuario(Long id) throws SgdServicesException {
		try {
			Optional<Usuario> u = usuarioRepository.findById(id);
			if (u.isPresent()) {
				return u.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuario de UsuarioService: " + e.getMessage() , e);
		}
		
	}

	public Usuario getUsuarioByUsername(String username) throws SgdServicesException {
		try {
			Iterable<Usuario> usuarios = usuarioRepository.findAll();
			List<Usuario> user = StreamSupport.stream(usuarios.spliterator(), false)
					.filter(u -> u.getUsername().equals(username)).toList();
			if (!user.isEmpty()) {
				return user.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getUsuarioByUsername de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en getUsuarioByUsername de UsuarioService: " + e.getMessage() , e);
		}
	}

	public Long saveOrUpdateUsuario(UsuarioDto userDto) throws SgdServicesException {
		try {
			Usuario user = new Usuario();
			if (userDto.getId() != null) {
				user = usuarioRepository.findById(userDto.getId()).get();
			}
			user.setFullname(userDto.getFullname());
			user.setCreationDate(userDto.getCreationDate());
			user.setPassword(userDto.getPassword());
			user.setUsername(userDto.getUsername());
			Rol r = rolRepo.findById(userDto.getIdRol()).get();
			user.setRol(r);
			user = usuarioRepository.save(user);
			return user.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateUsuario de UsuarioService: " + e.getMessage() , e);
		}
		
	}
	
	public List<UsuarioDto> getUsuarios() throws SgdServicesException {
		try {
			Iterable<Usuario> usuariosTodos = usuarioRepository.findAll();
	    	List<Usuario> usuarios = StreamSupport.stream(usuariosTodos.spliterator(), false).toList();
	        if (!usuarios.isEmpty()) {
	        	List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
	        	for (Usuario u : usuarios) {
	        		UsuarioDto uDto = new UsuarioDto(u.getId(), u.getUsername(), u.getPassword(), u.getCreationDate(), u.getFullname(), u.getRol().getId());
	        		usuariosDto.add(uDto);
	        	}
	        	return usuariosDto;
	        } else {
	            return null;
	        }
		} catch (Exception e) {
			log.error("Error en getUsuarios de UsuarioService: " + e.getMessage());
			e.printStackTrace();
			throw new SgdServicesException("Error en getUsuarios de UsuarioService: " + e.getMessage() , e);
		}
    	
    }
	
	public void deleteUsuario(Long id) throws SgdServicesException {
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Error en deleteUsuario de UsuarioService: " + e.getMessage());
			throw new SgdServicesException("Error en deleteUsuario de UsuarioService: " + e.getMessage() , e);
		}
		
	}

}
