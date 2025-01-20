package com.fhce.sgd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fhce.sgd.model.usuarios.CustomUsuarioDetails;
import com.fhce.sgd.model.usuarios.Usuario;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario user = usuarioService.getUsuarioByUsername(username);
			if (user == null) {
				log.error("Usuario no encontrado en la app: " + username);
				throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
			}
			return new CustomUsuarioDetails(user);
		} catch (SgdServicesException e) {
			log.error("Error en loadUserByUsername de CustomUsuarioDetailsService: " + e.getMessage());
			throw new UsernameNotFoundException("Usuario o contraseña incorrecta");
		}

	}

}
