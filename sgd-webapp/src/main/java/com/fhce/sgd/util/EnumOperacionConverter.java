package com.fhce.sgd.util;

import com.fhce.sgd.model.enums.EnumOperacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "operacionConverter", managed = true)
public class EnumOperacionConverter implements Converter<EnumOperacion>{
	
	@Override
    public EnumOperacion getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return EnumOperacion.valueOf(value);
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, EnumOperacion value) {
        if (value != null) {
            return value.name();
        }
        else {
            return null;
        }
    }

}
