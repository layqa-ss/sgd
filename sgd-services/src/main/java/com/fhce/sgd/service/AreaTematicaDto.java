package com.fhce.sgd.service;

import java.util.Objects;

public class AreaTematicaDto {

    private Long id;

    private String nombre;
    
    private Long carreraId;
    
    private String nombreCarrera;
    
    private boolean habilitada;
    
    public AreaTematicaDto() {
    	
    }

	public AreaTematicaDto(Long id, String nombre, Long carreraId, boolean habilitada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.carreraId = carreraId;
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

	public Long getCarreraId() {
		return carreraId;
	}

	public void setCarreraId(Long carreraId) {
		this.carreraId = carreraId;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, nombreCarrera);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaTematicaDto other = (AreaTematicaDto) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(nombreCarrera, other.nombreCarrera);
	}
    
}
