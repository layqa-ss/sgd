package com.fhce.sgd.model.gestion;

import java.util.List;
import java.util.Set;

import com.fhce.sgd.model.programas.MarcoAcademico;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidades_academicas")
public class UnidadAcademica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombreUA;

	@OneToMany(mappedBy = "ua")
	private List<Carrera> carreras;

	@ManyToMany(mappedBy = "unidadesAcademicas")
	private Set<MarcoAcademico> marcos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUA() {
		return nombreUA;
	}

	public void setNombreUA(String nombreUA) {
		this.nombreUA = nombreUA;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Set<MarcoAcademico> getMarcos() {
		return marcos;
	}

	public void setMarcos(Set<MarcoAcademico> marcos) {
		this.marcos = marcos;
	}

}
