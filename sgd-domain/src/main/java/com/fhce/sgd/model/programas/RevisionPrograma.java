package com.fhce.sgd.model.programas;

import java.util.Date;

import com.fhce.sgd.model.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "revisiones")
public class RevisionPrograma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Date fecha_revision;
	
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

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Date getFecha_revision() {
		return fecha_revision;
	}

	public void setFecha_revision(Date fecha_revision) {
		this.fecha_revision = fecha_revision;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
