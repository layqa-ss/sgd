package com.fhce.sgd.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.usuarios.CustomUsuarioDetails;

@Service
public class CustomUsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsuarioDto user = usuarioService.getUsuarioByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username or Password not found");
		}
		return new CustomUsuarioDetails(user.getUsername(), user.getPassword(), authorities(), user.getFullname());
	}

	public Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

}
