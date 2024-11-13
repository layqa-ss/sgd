package com.fhce.sgd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fhce.sgd.model.programas.AprobarPrograma;


public interface AprobacionRepository extends CrudRepository<AprobarPrograma, Long> {
	
	List<AprobarPrograma> findByProgramaId(Long idPrograma);

}
