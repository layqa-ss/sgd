package com.fhce.sgd.model.enums;

public enum EnumModalidad {
	
	PRESENCIAL("Presencial"),
	VIRTUAL("Virtual"),
	HIBRIDA("Híbrida"),
	SEMIPRESENCIAL("Semipresencial");
	
	public final String label;

    private EnumModalidad(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
