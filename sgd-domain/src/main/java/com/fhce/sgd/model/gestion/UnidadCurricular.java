package com.fhce.sgd.model.gestion;

import java.util.List;

import com.fhce.sgd.model.programas.Programa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidades_curriculares")
public class UnidadCurricular {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombreUC;
	
	@OneToMany(mappedBy="uc")
	private List<Programa> programas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUC() {
		return nombreUC;
	}

	public void setNombreUC(String nombreUC) {
		this.nombreUC = nombreUC;
	}

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

}
