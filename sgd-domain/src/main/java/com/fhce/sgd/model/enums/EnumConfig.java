package com.fhce.sgd.model.enums;

public enum EnumConfig {
	
	VIGENCIA_EN_ANIOS("Vigencia de un programa aprobado (años)"),
	EDICION_PROGRAMAS_DESDE("Edición de programas desde"),
	EDICION_PROGRAMAS_HASTA("Edición de programas hasta"),
	LINK_FORMULARIO_ENCARGADO("Formulario de autorización para que docentes grado 1 y 2 sean encargados/as");
	
	public final String label;

    private EnumConfig(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
