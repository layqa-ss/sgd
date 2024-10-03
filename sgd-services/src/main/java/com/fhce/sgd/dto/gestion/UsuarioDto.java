package com.fhce.sgd.dto.gestion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioDto {

    private Long id;

    private String username;

    private String password;
    
    private String fullname;

    private Date creationDate;
    
    public UsuarioDto() {
    	
    }

    public UsuarioDto(Long id, String username, String password, Date creationDate, String fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
        this.fullname = fullname;
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
}
