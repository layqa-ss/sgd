package com.fhce.sgd.model.usuarios;

import org.hibernate.envers.Audited;

import com.fhce.sgd.model.enums.EnumOperacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
@Audited
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	
	private EnumOperacion[] operaciones;
	
	public Rol(String nombre, EnumOperacion[] operaciones) {
		this.nombre = nombre;
		this.operaciones = operaciones;
	}

	public Rol() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumOperacion[] getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(EnumOperacion[] operaciones) {
		this.operaciones = operaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
