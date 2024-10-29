package com.fhce.sgd.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.enums.EnumOperacion;
import com.fhce.sgd.model.usuarios.CustomUsuarioDetails;

import jakarta.inject.Named;

@Named("appCtrl")
@Controller
public class AppController {



	@Value("${build.version}")
	private String buildVersion;

	private boolean puedeDescargarPdf = false;
	private boolean puedeEnviarCC = false;

	@GetMapping("/login")
	public String login(Model model, UsuarioDto userDto) {

		model.addAttribute("user", userDto);
		return "login";
	}


	public String getUsuarioLogueado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((CustomUsuarioDetails) principal).getFullname();
			cargarPermisos(((CustomUsuarioDetails) principal).getAuthorities());
		} else {
			username = principal.toString();
		}
		return username;
	}

	public void cargarPermisos(Collection<? extends GrantedAuthority> operaciones) {
		puedeDescargarPdf = operaciones.contains(EnumOperacion.DESCARGAR_REVISION_CC);
		puedeEnviarCC = operaciones.contains(EnumOperacion.ENVIAR_REVISION_CC);
	}

	public boolean isPuedeDescargarPdf() {
		return puedeDescargarPdf;
	}

	public boolean isPuedeEnviarCC() {
		return puedeEnviarCC;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

}