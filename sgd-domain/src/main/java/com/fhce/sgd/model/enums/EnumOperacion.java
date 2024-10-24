package com.fhce.sgd.model.enums;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public enum EnumOperacion implements Serializable, Comparable<EnumOperacion>, GrantedAuthority {
	
	GESTION_USUARIOS("Gestión de usuarios"),
	GESTION_UCA("Gestión de unidades académicas, carreras y áreas temáticas"),
	CREAR_PROGRAMA("Crear programa"),
	EDITAR_PROGRAMA("Editar programa"),
	ENVIAR_REVISION_CC("Enviar programa a revisión de Comisión de Carrera"),
	DESCARGAR_REVISION_CC("Descargar pdf en revisión de Comisión de Carrera"),
	SUGERENCIAS_REVISION_CC("Hacer sugerencias en revisión de Comisión de Carrera"),
	APROBAR_REVISION_CC("Aprobar revisión de Comisión de Carrera"),
	EDITAR_SEGUN_SUGERENCIAS("Editar según sugerencias"),
	APROBAR_BEDELIAS("Aprobar revisión Bedelías"),
	DESCARGAR_REVISION_CAG("Descargar pdf en revisión de Comisión Académica de grado"),
	SUGERENCIAS_REVISION_CAG("Hacer sugerencias en revisión de Comisión Académica de grado"),
	APROBAR_REVISION_CAG("Aprobar revisión de Comisión Académica de grado"),
	DESCARGAR_REVISION_CG("Descargar pdf en revisión Consejo/Gobierno"),
	SUGERENCIAS_REVISION_CG("Hacer sugerencias en revisión Consejo/Gobierno"),
	APROBAR_REVISION_CG("Aprobar revisión Consejo/Gobierno"),
	DESCARGAR_APROBADO("Descargar pdf una vez aprobado"),
	EDITAR_APROBADO("Editar una vez aprobado"),
	DESCARGAR_REVISION_CC_ABREV("Descargar pdf en revisión de Comisión de Carrera abreviado"),
	SUGERENCIAS_REVISION_CC_ABREV("Hacer sugerencias en revisión de Comisión de Carrera abreviado"),
	APROBAR_REVISION_CC_ABREV("Aprobar revisión de Comisión de Carrera abreviado"),
	EDITAR_SEGUN_SUGERENCIAS_ABREV("Editar según sugerencias abreviado"),
	VER_PROGRAMAS("Ver todos los programas"),
	VER_CAMBIOS_ESTADO("Ver cambios de estado de un programa");
	
	public final String label;
	
	private EnumOperacion(String label) {
    	this.label = label;
    }

	public String getLabel() {
		return label;
	}

	@Override
	public String getAuthority() {
		return this.name();
	}
}
