package com.fhce.sgd.repository;

import org.springframework.data.repository.CrudRepository;

import com.fhce.sgd.model.enums.EnumConfig;
import com.fhce.sgd.model.gestion.Configuracion;


public interface ConfigRepository extends CrudRepository<Configuracion, Long> {

	Configuracion findByConfig(EnumConfig config);
}
