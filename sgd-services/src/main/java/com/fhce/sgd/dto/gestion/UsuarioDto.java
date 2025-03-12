package com.fhce.sgd.dto.gestion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fhce.sgd.model.enums.EnumTipoAdscripcion;

public class UsuarioDto {

    private Long id;

    private String username;

    private String password;
    
    private String fullname;

    private Date creationDate;
    
    private Long idRol;
    
    private EnumTipoAdscripcion tipoAdscripcion;
    
    private List<UnidadAcademicaDto> unidades;
	
	private List<CarreraDto> carreras;
    
    public UsuarioDto() {
    	
    }

    public UsuarioDto(Long id, String username, Date creationDate, String fullname, Long idRol) {
        this.id = id;
        this.username = username;
        this.creationDate = creationDate;
        this.fullname = fullname;
        this.idRol = idRol;
    }

    public Long getId() {
        return id;
    }

	public String getPassword() {
		return password;
	}

	public Date getCreationDate() {
        return creationDate;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getCreationDateFormated() {
		return new SimpleDateFormat("dd/MM/yyyy").format(creationDate);
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public EnumTipoAdscripcion getTipoAdscripcion() {
		return tipoAdscripcion;
	}

	public void setTipoAdscripcion(EnumTipoAdscripcion tipoAdscripcion) {
		this.tipoAdscripcion = tipoAdscripcion;
	}

	public List<UnidadAcademicaDto> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadAcademicaDto> unidades) {
		this.unidades = unidades;
	}

	public List<CarreraDto> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<CarreraDto> carreras) {
		this.carreras = carreras;
	}
}
