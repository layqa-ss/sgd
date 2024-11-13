package com.fhce.sgd.dto.programas;

import java.util.List;

import com.fhce.sgd.dto.gestion.CarreraDto;
import com.fhce.sgd.dto.gestion.UnidadAcademicaDto;
import com.fhce.sgd.model.enums.EnumEstadoPrograma;

public class ProgramaDto {

	private Long id;
	private String nombreUC;
	private Integer year;
	private EnumEstadoPrograma estado;
	private Long idUsuario;
	
	private List<UnidadAcademicaDto> unidades;
	private List<CarreraDto> carreras;
	private List<ProgramaIntegranteDto> integrantes;

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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<UnidadAcademicaDto> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadAcademicaDto> unidades) {
		this.unidades = unidades;
	}

	public List<CarreraDto> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraDto> carreras) {
		this.carreras = carreras;
	}

	public List<ProgramaIntegranteDto> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<ProgramaIntegranteDto> integrantes) {
		this.integrantes = integrantes;
	}
	
}
