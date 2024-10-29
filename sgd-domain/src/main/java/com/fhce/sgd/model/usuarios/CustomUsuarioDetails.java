package com.fhce.sgd.model.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fhce.sgd.model.enums.EnumOperacion;

public class CustomUsuarioDetails implements UserDetails {
	
	private Usuario user;

	public CustomUsuarioDetails(Usuario u) {
		this.user = u;
	}

	public String getFullname() {
		return user.getFullname();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		EnumOperacion[] operaciones = user.getRol().getOperaciones();
		for (int i = 0; i < operaciones.length; i++) {
			authorities.add(operaciones[i]);
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
