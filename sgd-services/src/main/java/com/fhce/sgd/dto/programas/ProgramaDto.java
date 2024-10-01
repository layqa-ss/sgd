package com.fhce.sgd.dto.programas;

import com.fhce.sgd.model.enums.EnumEstadoPrograma;

public class ProgramaDto {

	private Long id;
	private String nombreUC;
	private Integer year;
	private EnumEstadoPrograma estado;

	public ProgramaDto(Long id, String nombre, Integer year, EnumEstadoPrograma estado) {
		this.id = id;
		this.nombreUC = nombre;
		this.year = year;
		this.estado = estado;
	}

	public String getNombreUC() {
		return nombreUC;
	}

	public void setNombreUC(String nombreUC) {
		this.nombreUC = nombreUC;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public EnumEstadoPrograma getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPrograma estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
