package com.fhce.sgd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.fhce.sgd.service.GestionService;
import com.fhce.sgd.service.GestionServiceImpl;
import com.fhce.sgd.service.UsuarioService;
import com.fhce.sgd.service.UsuarioServiceImpl;

@Configuration
@ImportResource("classpath:domain-applicationContext.xml")
public class ServicesContext {
    
    @Bean
    public UsuarioService usuarioService() {
        return new UsuarioServiceImpl();
    }
    
    @Bean
    public GestionService gestionService() {
        return new GestionServiceImpl();
    }
}
