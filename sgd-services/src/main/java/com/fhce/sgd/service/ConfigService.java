package com.fhce.sgd.service;

import java.util.List;

import com.fhce.sgd.dto.gestion.ConfigDto;
import com.fhce.sgd.model.enums.EnumConfig;
import com.fhce.sgd.service.exception.SgdServicesException;

public interface ConfigService {

	ConfigDto getConfigDto(Long id) throws SgdServicesException;
	
	ConfigDto getConfigDtoByEnum(EnumConfig enumC) throws SgdServicesException;
	
	Long saveOrUpdateConfig(ConfigDto configDto) throws SgdServicesException;
	
	List<ConfigDto> getConfigs() throws SgdServicesException;
}
