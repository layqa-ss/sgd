package com.fhce.sgd.model.programas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bibliografia")
public class Bibliografia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Lob
	@Column(length=512)
	private String titulo;
	
	private Integer orden;
	private boolean esTitulo;
	
	@ManyToOne
    @JoinColumn(name="id_programa", nullable=false)
	private Programa programa;
	
	public Bibliografia() {
		
	}
	
	public Bibliografia(String titulo) {
		this.titulo =  titulo;
	}

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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public boolean isEsTitulo() {
		return esTitulo;
	}

	public void setEsTitulo(boolean esTitulo) {
		this.esTitulo = esTitulo;
	}

}
