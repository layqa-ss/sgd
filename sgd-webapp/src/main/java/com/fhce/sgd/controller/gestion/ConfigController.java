package com.fhce.sgd.controller.gestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import com.fhce.sgd.dto.gestion.ConfigDto;
import com.fhce.sgd.service.ConfigService;
import com.fhce.sgd.service.exception.SgdServicesException;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

@Named("configCtrl")
@SessionScope
@Slf4j
public class ConfigController {

	@Autowired
	private ConfigService configService;

	private List<ConfigDto> configs;

	@PostConstruct
	public void init() {
		try {
			configs = configService.getConfigs();
		} catch (SgdServicesException e) {
			log.error("Error en init de ConfigController.");
		}
	}
	
	public void saveConfigs() {
		try {
			for (ConfigDto c : configs) {
				configService.saveOrUpdateConfig(c);
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Configuraciones guardadas"));
		} catch(SgdServicesException e) {
			log.error("Error en saveConfigs de ConfigController");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("No se ha podido actualizar las configuraciones"));
		}
	}

	public List<ConfigDto> getConfigs() {
		return configs;
	}

	public void setConfigs(List<ConfigDto> configs) {
		this.configs = configs;
	}

}