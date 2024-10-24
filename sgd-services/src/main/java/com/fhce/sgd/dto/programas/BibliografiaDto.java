package com.fhce.sgd.dto.programas;

public class BibliografiaDto {
	
	private Long id;

	private Integer orden;
	
	private String titulo;
	
	private boolean esTitulo;

	public BibliografiaDto() {
		
	}

	public BibliografiaDto(Long id, Integer orden, String titulo, boolean esTitulo) {
		this.id = id;
		this.orden = orden;
		this.titulo = titulo;
		this.esTitulo = esTitulo;
	}


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEsTitulo() {
		return esTitulo;
	}

	public void setEsTitulo(boolean esTitulo) {
		this.esTitulo = esTitulo;
	}

}
