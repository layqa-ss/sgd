package com.fhce.sgd.model.programas;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fhce.sgd.model.enums.EnumEstadoPrograma;
import com.fhce.sgd.model.usuarios.Usuario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AccionPrograma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@CreationTimestamp
	private Date fecha;
	
	private EnumEstadoPrograma estado;
	
	@ManyToOne
    @JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
    @JoinColumn(name="id_programa", nullable=false)
	private Programa programa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public EnumEstadoPrograma getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoPrograma estado) {
		this.estado = estado;
	}

}
