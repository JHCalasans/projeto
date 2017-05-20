package br.com.ido.qpedido.acl;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ido.qpedido.util.FacesUtil;
import br.com.ido.qpedido.util.Paginas;

@SuppressWarnings("unused")
public class FaseControleAcesso implements PhaseListener {

	private static final long serialVersionUID = 6905520550310490996L;

	public FaseControleAcesso() {
		initLevels();
	}

	private void initLevels() {
	}

	private boolean matchUri(List<UrlFilter> list, String uri) {
		for (UrlFilter filter : list) {
			if (filter.matches(uri))
				return true;
		}
		return false;
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String idPagina = facesContext.getViewRoot().getViewId();
		if ((session == null) && (!idPagina.equals(Paginas.PAG_SESSAO_ENCERRADA)))
			FacesUtil.redirecionar(null, Paginas.PAG_SESSAO_ENCERRADA, true, null);

		ExternalContext externalContext = facesContext.getExternalContext();

		Object request = externalContext.getRequest();
		String retURL = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest http_req = (HttpServletRequest) request;
			retURL = http_req.getRequestURI();
		}

		boolean paginaDeLogin = idPagina.equals(Paginas.PAG_SESSAO_ENCERRADA);
		boolean paginaSessaoEncerrada = idPagina.equals(Paginas.PAG_SESSAO_ENCERRADA);
		boolean acessaSemLogin = true;

		//FacesUtil.redirecionar(null, null, true, null);
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
