package com.fhce.sgd.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fhce.sgd.dto.gestion.UsuarioDto;
import com.fhce.sgd.model.usuarios.CustomUsuarioDetails;
import com.fhce.sgd.service.UsuarioService;

import jakarta.inject.Named;

@Named("appCtrl")
@Controller
public class AppController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, UsuarioDto userDto) {

		model.addAttribute("user", userDto);
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model, UsuarioDto userDto) {
		model.addAttribute("user", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerSava(@ModelAttribute("user") UsuarioDto userDto, Model model) {
		UsuarioDto user = usuarioService.getUsuarioByUsername(userDto.getUsername());
		if (user != null) {
			model.addAttribute("Userexist", user);
			return "register";
		}
		usuarioService.save(userDto);
		return "redirect:/register?success";
	}
	
	public String getUsuarioLogueado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username ="";
	    if (principal instanceof UserDetails) {
	      username = ((CustomUsuarioDetails)principal).getFullname();
	    } else {
	       username = principal.toString();
	    }
	    return username;
	}

}