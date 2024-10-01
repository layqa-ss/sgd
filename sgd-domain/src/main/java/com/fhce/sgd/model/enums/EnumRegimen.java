package com.fhce.sgd.model.enums;

public enum EnumRegimen {
	
	LIBRE("Asistencia libre"),
	OBLIGATORIA_75_DICTADAS("Asistencia obligatoria al 75% de las clases dictadas"),
	OBLIGATORIA_75_PRACTICAS("Asistencia obligatoria al 75% de las clases prácticas");
	
	public final String label;

    private EnumRegimen(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
