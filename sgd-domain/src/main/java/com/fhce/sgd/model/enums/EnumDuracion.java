package com.fhce.sgd.model.enums;

public enum EnumDuracion {
	
	SEMESTRAL("Semestral"),
	ANUAL("Anual"),
	OTRO("Otro");
	
	public final String label;

    private EnumDuracion(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
