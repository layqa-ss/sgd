package com.fhce.sgd.dto.programas;

public class BibliografiaDto {
	
	private Long id;

	private Integer orden;
	
	private String titulo;

	public BibliografiaDto() {
		
	}

	public BibliografiaDto(Long id, Integer orden, String titulo) {
		this.id = id;
		this.orden = orden;
		this.titulo = titulo;
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

}
