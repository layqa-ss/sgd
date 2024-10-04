package com.fhce.sgd.model.enums;

public enum EnumRolDocente {
	
	RESPONSABLE("Responsable"),
	ENCARGADO("Encargado/a"),
	APOYO("Docente de apoyo"),
	INVITADO("Invitado/a");
	
	public final String label;

    private EnumRolDocente(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
