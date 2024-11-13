package com.fhce.sgd.dto.gestion;

import com.fhce.sgd.model.enums.EnumConfig;

public class ConfigDto {

	private Long id;

	private EnumConfig config;

	private String value;

	private boolean isDate;

	public ConfigDto() {

	}

	public ConfigDto(Long id, EnumConfig config, String value, boolean isDate) {
		super();
		this.id = id;
		this.config = config;
		this.value = value;
		this.isDate = isDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumConfig getConfig() {
		return config;
	}

	public void setConfig(EnumConfig config) {
		this.config = config;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isDate() {
		return isDate;
	}

	public void setDate(boolean isDate) {
		this.isDate = isDate;
	}

}
