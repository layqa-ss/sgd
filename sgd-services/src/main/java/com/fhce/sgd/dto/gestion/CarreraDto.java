package com.fhce.sgd.dto.gestion;

import java.util.Objects;

public class CarreraDto {

    private Long id;

    private String nombre;
    
    private Long uaId;
    
    private String nombreUA;
    
    private Long area;
    
    private String areaNombre;
    
    public CarreraDto() {
    	
    }

	public CarreraDto(Long id, String nombre, Long uaId, String nombreUA) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.uaId = uaId;
		this.nombreUA = nombreUA;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getUaId() {
		return uaId;
	}

	public String getNombreUA() {
		return nombreUA;
	}

	public void setNombreUA(String nombreUA) {
		this.nombreUA = nombreUA;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUaId(Long uaId) {
		this.uaId = uaId;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public String getAreaNombre() {
		return areaNombre;
	}

	public void setAreaNombre(String areaNombre) {
		this.areaNombre = areaNombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, nombreUA, uaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarreraDto other = (CarreraDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(nombreUA, other.nombreUA) && Objects.equals(uaId, other.uaId);
	}
   
}