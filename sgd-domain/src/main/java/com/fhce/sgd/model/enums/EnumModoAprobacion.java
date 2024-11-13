package com.fhce.sgd.model.enums;

public enum EnumModoAprobacion {
	
	POR_CURSO("Aprobación por curso, sin examen ni otro tipo de evaluación final"),
	POR_EVALUACION("Aprobación por evaluación final obligatoria (examen, monografía, ensayo final, etc)"),
	POR_EXONERACION("Aprobación por exoneración (aprobación directa) o por evaluación final");
	
	public final String label;

    private EnumModoAprobacion(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
