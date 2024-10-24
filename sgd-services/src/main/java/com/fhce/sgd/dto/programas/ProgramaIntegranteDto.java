package com.fhce.sgd.dto.programas;

import com.fhce.sgd.model.enums.EnumCargo;
import com.fhce.sgd.model.enums.EnumRolDocente;

public class ProgramaIntegranteDto {
	
	private Long id;

	private EnumRolDocente rol;
	
	private EnumCargo cargo;
	
	private String cargoOtro;
	
	private String unidad_academica;
	
	private String subunidad_academica;
	
	private Long idUsuario;
	
	private String nombre_docente;

	public ProgramaIntegranteDto() {
		
	}

	public ProgramaIntegranteDto(Long id, EnumRolDocente rol, EnumCargo cargo, String cargoOtro, String ua, String sua, Long idUsuario, String nombreDocente) {
		this.id = id;
		this.rol = rol;
		this.cargo = cargo;
		this.cargoOtro = cargoOtro;
		this.unidad_academica = ua;
		this.subunidad_academica = sua;
		this.idUsuario = idUsuario;
		this.nombre_docente = nombreDocente;
	}


	public EnumRolDocente getRol() {
		return rol;
	}


	public void setRol(EnumRolDocente rol) {
		this.rol = rol;
	}


	public EnumCargo getCargo() {
		return cargo;
	}


	public void setCargo(EnumCargo cargo) {
		this.cargo = cargo;
	}


	public String getUnidad_academica() {
		return unidad_academica;
	}


	public void setUnidad_academica(String unidad_academica) {
		this.unidad_academica = unidad_academica;
	}


	public String getSubunidad_academica() {
		return subunidad_academica;
	}


	public void setSubunidad_academica(String subunidad_academica) {
		this.subunidad_academica = subunidad_academica;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombre_docente() {
		return nombre_docente;
	}
	
	public void setNombre_docente(String nombre_docente) {
		this.nombre_docente = nombre_docente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargoOtro() {
		return cargoOtro;
	}

	public void setCargoOtro(String cargoOtro) {
		this.cargoOtro = cargoOtro;
	}

}
