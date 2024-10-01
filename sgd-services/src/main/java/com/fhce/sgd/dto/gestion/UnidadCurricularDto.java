package com.fhce.sgd.dto.gestion;

public class UnidadCurricularDto {

    private Long id;

    private String nombre;
    
    public UnidadCurricularDto() {
    	
    }

	public UnidadCurricularDto(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
    
}
