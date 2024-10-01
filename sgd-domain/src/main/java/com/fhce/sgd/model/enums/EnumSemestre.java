package com.fhce.sgd.model.enums;

public enum EnumSemestre {
	
	PAR("Par"),
	IMPAR("Impar"),
	AMBOS("Ambos");
	
	public final String label;

    private EnumSemestre(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

}
