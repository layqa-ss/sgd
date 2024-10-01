package com.fhce.sgd.model.enums;

public enum EnumEstadoPrograma {
	
	CREADO("Creado", 1),
	REVISION_CC("Revisión Comisión de Carrera", 2),
	SUGERENCIAS("Sugerencias", 3),
	BEDELIAS("Bedelías", 4),
	REVISION_CAG("Revisión Comisión Académica de grado", 5),
	REVISION_CG("Revisión Consejo/Gobierno", 6),
	APROBADO("Aprobado", 7),
	REVISION_CC_ABR("Revisión Comisión de Carrera (abreviado)", 8),
	SUGERENCIAS_ABR("Sugerencias (abreviado)", 9);
	
	public final String label;
	public final Integer step;

    private EnumEstadoPrograma(String label, Integer step) {
    	this.label = label;
    	this.step = step;
    }

	public String getLabel() {
		return label;
	}
	
	public Integer getStep() {
		return step;
	}
}
