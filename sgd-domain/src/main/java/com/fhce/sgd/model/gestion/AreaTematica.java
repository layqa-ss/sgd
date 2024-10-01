package com.fhce.sgd.model.gestion;

import java.util.Set;

import com.fhce.sgd.model.programas.MarcoAcademico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "areas")
public class AreaTematica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombreArea;
	
	@ManyToOne
    @JoinColumn(name="id_carrera", nullable=false)
	private Carrera carrera;
	
	@ManyToMany(mappedBy = "areasTematicas")
	private Set<MarcoAcademico> marcos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Set<MarcoAcademico> getMarcos() {
		return marcos;
	}

	public void setMarcos(Set<MarcoAcademico> marcos) {
		this.marcos = marcos;
	}

}
