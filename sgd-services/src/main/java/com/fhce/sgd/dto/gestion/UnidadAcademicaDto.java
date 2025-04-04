package com.fhce.sgd.dto.gestion;

import java.util.Objects;

public class UnidadAcademicaDto {

    private Long id;

    private String nombre;
    
    private boolean habilitada;
    
    public UnidadAcademicaDto() {
    	
    }

	public UnidadAcademicaDto(Long id, String nombre, boolean habilitada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.habilitada = habilitada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadAcademicaDto other = (UnidadAcademicaDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
    
}
