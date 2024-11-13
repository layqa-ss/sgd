package com.fhce.sgd.dto.programas;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fhce.sgd.model.enums.EnumEstadoPrograma;

public class AccionDto {
	
	private Long id;
	private Date fecha_revision;
	private Long idUsuario;
	private Long idPrograma;
	private String nombre_usuario;
	private EnumEstadoPrograma estadoPrograma;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha_revision() {
		return fecha_revision;
	}
	public String getFecha_revisionFormated() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fecha_revision);
	}

	public void setFecha_revision(Date fecha_revision) {
		this.fecha_revision = fecha_revision;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public EnumEstadoPrograma getEstadoPrograma() {
		return estadoPrograma;
	}
	public void setEstadoPrograma(EnumEstadoPrograma estadoPrograma) {
		this.estadoPrograma = estadoPrograma;
	}

}
