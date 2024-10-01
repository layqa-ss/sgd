package com.fhce.sgd.model.enums;

public enum EnumFormato {
	
	TEORICA("Teórica"),
	PRACTICA("Práctica"),
	TP("Teórica-práctica");
	
	public final String label;

    private EnumFormato(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
