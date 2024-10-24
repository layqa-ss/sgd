package com.fhce.sgd.model.enums;

public enum EnumCargo {
	
	AYUDANTE("Ayudante"),
	ASISTENTE("Asistente"),
	ADJUNTO("Prof. Adjunto/a"),
	AGREGADO("Prof. Agregado/a"),
	TITULAR("Prof. Titular"),
	LIBRE("Docente libre"),
	OTRO("Otro");
	
	public final String label;

    private EnumCargo(String label) {
    	this.label = label;
    }
    
    public String getLabel() {
		return label;
	}

}
