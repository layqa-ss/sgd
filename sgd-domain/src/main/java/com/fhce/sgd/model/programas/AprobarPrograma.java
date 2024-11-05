package com.fhce.sgd.model.programas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "aprobaciones")
public class AprobarPrograma extends AccionPrograma {

	private String comentarios;
	private String despachoFileName;
	@Lob
	@Column(length=1048576)
	private byte[] despachoData;

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getDespachoFileName() {
		return despachoFileName;
	}

	public void setDespachoFileName(String despachoFileName) {
		this.despachoFileName = despachoFileName;
	}

	public byte[] getDespachoData() {
		return despachoData;
	}

	public void setDespachoData(byte[] despachoData) {
		this.despachoData = despachoData;
	}
}
