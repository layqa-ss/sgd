package com.fhce.sgd.model.programas;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.envers.Audited;

import com.fhce.sgd.model.gestion.AreaTematica;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "marcos")
@Audited
public class MarcoAcademico {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne
    @JoinColumn(name="id_programa")
	private Programa programa;
	
	@ManyToMany
	@JoinTable(
			  name = "marco_ua", 
			  joinColumns = @JoinColumn(name = "marco_id"), 
			  inverseJoinColumns = @JoinColumn(name = "ua_id"))
	private Set<UnidadAcademica> unidadesAcademicas;
	
	@ManyToMany
	@JoinTable(
			  name = "marco_carrera", 
			  joinColumns = @JoinColumn(name = "marco_id"), 
			  inverseJoinColumns = @JoinColumn(name = "carrera_id"))
	private Set<Carrera> carreras;
	
	@ManyToMany
	@JoinTable(
			  name = "marco_area", 
			  joinColumns = @JoinColumn(name = "marco_id"), 
			  inverseJoinColumns = @JoinColumn(name = "area_id"))
	private Set<AreaTematica> areasTematicas;
	
	public MarcoAcademico() {
		unidadesAcademicas = new HashSet<>();
		carreras = new HashSet<>();
		areasTematicas = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Set<UnidadAcademica> getUnidadesAcademicas() {
		return unidadesAcademicas;
	}

	public void setUnidadesAcademicas(Set<UnidadAcademica> unidadesAcademicas) {
		this.unidadesAcademicas = unidadesAcademicas;
	}

	public Set<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(Set<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Set<AreaTematica> getAreasTematicas() {
		return areasTematicas;
	}

	public void setAreasTematicas(Set<AreaTematica> areasTematicas) {
		this.areasTematicas = areasTematicas;
	}

}
