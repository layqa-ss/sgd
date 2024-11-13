package com.fhce.sgd.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fhce.sgd.model.programas.RevisionPrograma;


public interface RevisionRepository extends CrudRepository<RevisionPrograma, Long> {
	
	List<RevisionPrograma> findByProgramaId(Long idPrograma);

}
