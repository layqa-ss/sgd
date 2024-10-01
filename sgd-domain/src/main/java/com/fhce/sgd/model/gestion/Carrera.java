package com.fhce.sgd.model.gestion;

import java.util.List;
import java.util.Set;

import com.fhce.sgd.model.programas.MarcoAcademico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carreras")
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombreCarrera;
	
	@OneToMany(mappedBy="carrera")
	private List<AreaTematica> areas;
	
	@ManyToOne
    @JoinColumn(name="id_ua", nullable=false)
	private UnidadAcademica ua;
	
	@ManyToMany(mappedBy = "carreras")
	private Set<MarcoAcademico> marcos;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public List<AreaTematica> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaTematica> areas) {
		this.areas = areas;
	}

	public UnidadAcademica getUa() {
		return ua;
	}

	public void setUa(UnidadAcademica ua) {
		this.ua = ua;
	}

	public Set<MarcoAcademico> getMarcos() {
		return marcos;
	}

	public void setMarcos(Set<MarcoAcademico> marcos) {
		this.marcos = marcos;
	}

}
