package com.fhce.sgd.model.enums;

public enum EnumTipoAdscripcion {
	
	UA("Por unidad académica"),
	CARRERA("Por carrera");
	
	public final String label;

    private EnumTipoAdscripcion(String label) {
    	this.label = label;
    }
    
    public String getLabel() {
		return label;
	}

}
