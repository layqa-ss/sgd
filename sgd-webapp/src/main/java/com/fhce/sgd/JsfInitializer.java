package com.fhce.sgd;

import com.sun.faces.config.FacesInitializer;
import jakarta.faces.component.UIInput;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;

public class JsfInitializer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
		servletContext.setInitParameter(UIInput.EMPTY_STRING_AS_NULL_PARAM_NAME, Boolean.TRUE.toString());
		servletContext.setInitParameter("jakarta.faces.FACELETS_SKIP_COMMENTS", "true");
		servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
		servletContext.setInitParameter("jakarta.faces.STATE_SAVING_METHOD", "client");

		EnhancedListener cdiInitializer = new EnhancedListener();
		cdiInitializer.onStartup(null, servletContext);

		ServletContainerInitializer facesInitializer = new FacesInitializer();

		facesInitializer.onStartup(null, servletContext);
	}

}
