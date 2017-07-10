package br.com.ido.mesirva.util;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ViewExpiredExceptionExceptionHandler extends
		ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents()
				.iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable t = context.getException();
			if (t instanceof ViewExpiredException) {
				// ViewExpiredException vee = (ViewExpiredException) t;
				// FacesContext fc = FacesContext.getCurrentInstance();
				// Map<String, Object> requestMap =
				// fc.getExternalContext().getRequestMap();
				// NavigationHandler nav =
				// fc.getApplication().getNavigationHandler();
				try {
					// Push some useful stuff to the request scope for
					// use in the page
					// requestMap.put("currentViewId", vee.getViewId());
					FacesUtil.redirecionar(null, Paginas.PAG_SESSAO_ENCERRADA,
							true, null);
				} finally {
					i.remove();
				}
			}
		}
		// At this point, the queue will not contain any ViewExpiredEvents.
		// Therefore, let the parent handle them.
		getWrapped().handle();

	}
}