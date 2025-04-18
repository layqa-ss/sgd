package com.fhce.sgd.model.programas;

import org.hibernate.envers.Audited;

import com.fhce.sgd.model.enums.EnumCargo;
import com.fhce.sgd.model.enums.EnumRolDocente;
import com.fhce.sgd.model.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "integrantes")
@Audited
public class ProgramaIntegrante {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="id_docente")
	private Usuario docente;
	
	private EnumRolDocente rol;
	
	private EnumCargo cargo;
	
	private String cargoOtro;
	
	private String unidad_academica;
	
	private String subunidad_academica;
	
	@ManyToOne
    @JoinColumn(name="id_programa")
	private Programa programa;
	
	public ProgramaIntegrante() {
		
	}
	
	public ProgramaIntegrante(EnumRolDocente rol, EnumCargo cargo, String cargoOtro, String unidad_academica, String subunidad_academica) {
		this.rol =  rol;
		this.cargo = cargo;
		this.cargoOtro = cargoOtro;
		this.unidad_academica = unidad_academica;
		this.subunidad_academica = subunidad_academica;
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

	public Usuario getDocente() {
		return docente;
	}

	public void setDocente(Usuario docente) {
		this.docente = docente;
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

	public String getCargoOtro() {
		return cargoOtro;
	}

	public void setCargoOtro(String cargoOtro) {
		this.cargoOtro = cargoOtro;
	}

}
