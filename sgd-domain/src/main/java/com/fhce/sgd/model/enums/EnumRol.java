package com.fhce.sgd.model.enums;

public enum EnumRol {
	
	RESPONSABLE("Responsable"),
	ENCARGADO("Encargado/a"),
	APOYO("Docente de apoyo"),
	INVITADO("Invitado/a");
	
	public final String label;

    private EnumRol(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
