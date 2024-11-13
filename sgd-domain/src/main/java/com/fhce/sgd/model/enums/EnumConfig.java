package com.fhce.sgd.model.enums;

public enum EnumConfig {
	
	VIGENCIA_EN_ANIOS("Vigencia de un programa aprobado (años)"),
	EDICION_PROGRAMAS_DESDE("Edición de programas desde"),
	EDICION_PROGRAMAS_HASTA("Edición de programas hasta");
	
	public final String label;

    private EnumConfig(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
