package com.fhce.sgd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhce.sgd.dto.gestion.ConfigDto;
import com.fhce.sgd.model.enums.EnumConfig;
import com.fhce.sgd.model.gestion.Configuracion;
import com.fhce.sgd.repository.ConfigRepository;
import com.fhce.sgd.service.exception.SgdServicesException;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigRepository configRepo;

	public ConfigDto getConfigDto(Long id) throws SgdServicesException {
		try {
			Configuracion u = configRepo.findById(id).orElse(null);
			if (u != null) {
				ConfigDto cDto = new ConfigDto(u.getId(), u.getConfig(), u.getValue(), u.isDate());
				return cDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getConfigDto de ConfigService: " + e.getMessage());
			throw new SgdServicesException("Error en getConfigDto de ConfigService: " + e.getMessage() , e);
		}
		
	}
	
	public ConfigDto getConfigDtoByEnum(EnumConfig enumC) throws SgdServicesException {
		try {
			Configuracion u = configRepo.findByConfig(enumC);
			if (u != null) {
				ConfigDto cDto = new ConfigDto(u.getId(), u.getConfig(), u.getValue(), u.isDate());
				return cDto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("Error en getConfigDtoByEnum de ConfigService: " + e.getMessage());
			throw new SgdServicesException("Error en getConfigDtoByEnum de ConfigService: " + e.getMessage() , e);
		}
		
	}

	public Long saveOrUpdateConfig(ConfigDto configDto) throws SgdServicesException {
		try {
			Configuracion c = new Configuracion();
			if (configDto.getId() != null) {
				c = configRepo.findById(configDto.getId()).get();
			}
			c.setConfig(configDto.getConfig());
			c.setValue(configDto.getValue());
			c.setDate(configDto.isDate());
			c = configRepo.save(c);
			return c.getId();
		} catch (Exception e) {
			log.error("Error en saveOrUpdateConfig de ConfigService: " + e.getMessage());
			throw new SgdServicesException("Error en saveOrUpdateConfig de ConfigService: " + e.getMessage() , e);
		}
		
	}
	
	public List<ConfigDto> getConfigs() throws SgdServicesException {
		try {
			Iterable<Configuracion> configs = configRepo.findAll();
	      
        	List<ConfigDto> configsDto = new ArrayList<ConfigDto>();
        	for (Configuracion c : configs) {
        		ConfigDto cDto = new ConfigDto(c.getId(), c.getConfig(), c.getValue(), c.isDate());
        		configsDto.add(cDto);
        	}
        	return configsDto;
		} catch (Exception e) {
			log.error("Error en getConfigs de ConfigService: " + e.getMessage());
			e.printStackTrace();
			throw new SgdServicesException("Error en getConfigs de ConfigService: " + e.getMessage() , e);
		}
    	
    }

}
