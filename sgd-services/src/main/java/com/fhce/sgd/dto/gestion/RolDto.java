package com.fhce.sgd.dto.gestion;

import java.util.Arrays;
import java.util.Objects;

import com.fhce.sgd.model.enums.EnumOperacion;

public class RolDto {
	
	private Long id;
	private String nombre;
	private EnumOperacion[] operaciones;
	public RolDto(Long id, String nombre, EnumOperacion[] operaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.operaciones = operaciones;
	}
	
	public RolDto() {
		
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

	public EnumOperacion[] getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(EnumOperacion[] operaciones) {
		this.operaciones = operaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(operaciones);
		result = prime * result + Objects.hash(id, nombre);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolDto other = (RolDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Arrays.equals(operaciones, other.operaciones);
	}

}
