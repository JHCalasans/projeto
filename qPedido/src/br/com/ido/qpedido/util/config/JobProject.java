package br.com.ido.qpedido.util.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.ido.qpedido.util.ExcecoesUtil;

public class JobProject implements Job {

	public JobProject() {

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// TODO: Chamar aqui o metodo a ser executado
		} catch (Exception ex) {
			ExcecoesUtil.TratarExcecao(ex);
		}
	}
}
