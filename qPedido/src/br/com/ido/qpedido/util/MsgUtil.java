package br.com.ido.qpedido.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MsgUtil {
	public static void updateMessage(Severity severidadeMsg, String sumario,
			String detalhe) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severidadeMsg, sumario, detalhe));
	}
}
