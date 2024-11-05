package com.fhce.sgd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;

@SpringBootApplication
public class SGDApplication extends SpringBootServletInitializer implements ServletContextAware {

	public static void main(String[] args) {
		SpringApplication.run(SGDApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SGDApplication.class);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}

	@Bean
	public FacesServlet facesServlet() {
		return new FacesServlet();
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> facesServletRegistration() {
		ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<FacesServlet>(facesServlet(),
				"*.jsf");
		registration.setName("FacesServlet");
		registration.setLoadOnStartup(1);
		registration.setMultipartConfig(new MultipartConfigElement(
				"", 1048576, 5242880, 0));
		return registration;
	}

	@Bean
	public ServletContextInitializer facesInitializer() {
		return new JsfInitializer();
	}

}
