package com.fhce.sgd.dto.programas;

public class AprobarDto extends AccionDto {
	
	private String comentarios;
	private String despachoFileName;
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
