package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public UsuarioDto getUsuario(Long id) {
		Optional<Usuario> u = usuarioRepository.findById(id);
		if (u.isPresent()) {
			return new UsuarioDto(u.get().getId(), u.get().getUsername(), u.get().getPassword(),
					u.get().getCreationDate(), u.get().getFullname());
		} else {
			return null;
		}
	}

	public UsuarioDto getUsuarioByUsername(String username) {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		List<Usuario> user = StreamSupport.stream(usuarios.spliterator(), false)
				.filter(u -> u.getUsername().equals(username)).toList();
		if (!user.isEmpty()) {
			Usuario u = user.get(0);
			return new UsuarioDto(u.getId(), u.getUsername(), u.getPassword(), new Date(), u.getFullname());
		} else {
			return null;
		}
	}

	public Long save(UsuarioDto userDto) {
		Usuario user = new Usuario(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
				userDto.getFullname());
		user = usuarioRepository.save(user);
		return user.getId();
	}
	
	public List<UsuarioDto> getUsuarios() {
    	Iterable<Usuario> usuariosTodos = usuarioRepository.findAll();
    	List<Usuario> usuarios = StreamSupport.stream(usuariosTodos.spliterator(), false).toList();
        if (!usuarios.isEmpty()) {
        	List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
        	for (Usuario u : usuarios) {
        		UsuarioDto uDto = new UsuarioDto(u.getId(), u.getUsername(), u.getPassword(), u.getCreationDate(), u.getFullname());
        		usuariosDto.add(uDto);
        	}
        	return usuariosDto;
        } else {
            return null;
        }
    }
	
	public void deleteUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

}
