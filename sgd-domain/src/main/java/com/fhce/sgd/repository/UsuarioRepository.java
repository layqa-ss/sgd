package com.fhce.sgd.repository;

import org.springframework.data.repository.CrudRepository;

import com.fhce.sgd.model.usuarios.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
