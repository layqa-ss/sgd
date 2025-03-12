package com.fhce.sgd.model.usuarios;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

import com.fhce.sgd.model.enums.EnumTipoAdscripcion;
import com.fhce.sgd.model.gestion.Carrera;
import com.fhce.sgd.model.gestion.UnidadAcademica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
@Audited
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	
	private String fullname;

	@CreationTimestamp
	private Date creationDate;
	
	@ManyToOne
    @JoinColumn(name="id_rol")
	private Rol rol;
	
	private EnumTipoAdscripcion tipoAdscripcion;
	
	@ManyToMany
	@JoinTable(
			  name = "adscripcion_ua", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "ua_id"))
	private Set<UnidadAcademica> unidadesAcademicas;
	
	@ManyToMany
	@JoinTable(
			  name = "adscripcion_carrera", 
			  joinColumns = @JoinColumn(name = "usuario_id"), 
			  inverseJoinColumns = @JoinColumn(name = "carrera_id"))
	private Set<Carrera> carreras;
	
	public Usuario(String username, String fullname) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.creationDate = new Date();
	}
	
	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public EnumTipoAdscripcion getTipoAdscripcion() {
		return tipoAdscripcion;
	}

	public void setTipoAdscripcion(EnumTipoAdscripcion tipoAdscripcion) {
		this.tipoAdscripcion = tipoAdscripcion;
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
}
