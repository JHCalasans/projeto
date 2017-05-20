package br.com.ido.qpedido.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "removeMaskCPFCNPJConverter")
public class RemoveMaskCPFCNPJConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			value = value.toString().replaceAll("[- /.]", "");
			value = value.toString().replaceAll("[-()]", "");
			return value;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {		
		return value.toString();
	}
}