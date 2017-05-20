package br.com.ido.qpedido.acl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.sun.faces.application.ActionListenerImpl;

import br.com.ido.qpedido.util.ExcecoesUtil;
import br.com.ido.qpedido.util.FacesUtil;
import br.com.ido.qpedido.util.Paginas;

public class ExceptionHandler extends ActionListenerImpl {

	@Override
	public void processAction(ActionEvent event) {
		// Obtem o contexto JSF
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			// Executa o método da classe Pai
			super.processAction(event);
		} catch (Exception e) {

			// Se ocorrer um erro inesperado, exibe a mensagem abaixo
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(),
					null));
			// Salva o Log de Erro
			ExcecoesUtil.logarErro(e);
			FacesUtil.redirecionar(null, Paginas.PAG_ERRO, true, null);
		}
	}
}
