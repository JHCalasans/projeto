package br.com.ido.mesirva.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.el.ELResolver;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.ido.mesirva.enums.ConstantesEnum;

public final class FacesUtil {

	private FacesUtil() {
	}

	public final static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static Object eval(String str) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELResolver resolver = context.getApplication().getELResolver();
		return resolver.getValue(context.getELContext(), null, str);
	}

	public final static void redirecionar(String fromOutcome, String toOutcome,
			boolean facesRedirect, List<String> mensagens) {
		if (mensagens != null) {
			for (String msg : mensagens) {
				getFacesContext().addMessage(null, new FacesMessage(msg));
			}
			getFacesContext().getExternalContext().getFlash()
					.setKeepMessages(true);
		}
		getFacesContext()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(
						getFacesContext(),
						fromOutcome,
						toOutcome
								+ (facesRedirect ? "?faces-redirect=true" : ""));
	}

	public final static void redirecionarComViewParams(String fromOutcome,
			String toOutcome, boolean facesRedirect) {
		getFacesContext()
				.getApplication()
				.getNavigationHandler()
				.handleNavigation(
						getFacesContext(),
						fromOutcome,
						toOutcome
								+ (facesRedirect ? "?faces-redirect=true&includeViewParams=true"
										: "?includeViewParams=true"));

	}

	public final static String getApplicationUri() {
		try {
			FacesContext ctxt = FacesContext.getCurrentInstance();
			ExternalContext ext = ctxt.getExternalContext();
			URI uri = new URI(ext.getRequestScheme(), null,
					ext.getRequestServerName(), ext.getRequestServerPort(),
					ext.getRequestContextPath(), null, null);
			return uri.toASCIIString();
		} catch (URISyntaxException e) {
			throw new FacesException(e);
		}
	}
	
	public static Map<String, Object> getSessionMap() {
		if (FacesContext.getCurrentInstance() != null
				&& FacesContext.getCurrentInstance().getExternalContext() != null) {
			return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		}
		return null;
	}

	public static Integer getCodEnderecoEmpresa() {
		Integer codigo = (Integer) getSessionMap().get(ConstantesEnum.CHAVE_SESSION_END_EMPRESA.getDescricao());
		if (codigo != null) {
			return codigo;
		}
		return 1;// TODO: Remover
	}
}
