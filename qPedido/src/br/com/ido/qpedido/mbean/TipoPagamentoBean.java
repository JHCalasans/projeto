/*package br.com.ido.qpedido.mbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.bo.CadastroBO;
import br.com.ido.qpedido.entity.qpedido.TipoPagamento;
import br.com.ido.qpedido.entity.qpedido.TipoPagamentoEnderecoEmpresa;
import br.com.ido.qpedido.util.ExcecoesUtil;

@ManagedBean(name = TipoPagamentoBean.NOME_BEAN)
@ViewScoped
public class TipoPagamentoBean extends SimpleController {

	private static final long serialVersionUID = 2994258071586298208L;

	public static final String NOME_BEAN = "tipoPagamentoBean";

	private List<TipoPagamentoEnderecoEmpresa> listaTiposPagamentoEmpresa;

	private List<TipoPagamento> listaTiposPagamento;

	private TipoPagamentoEnderecoEmpresa tipoPagamento;

	private Integer codigoTipoPagamento;

	private TipoPagamentoEnderecoEmpresa tipoPagamentoSelecionado;

	@PostConstruct
	public void carregar() {
		try {
			listaTiposPagamento = CadastroBO.getInstance().obterTiposPagamento();
			if (getFacesContext().isPostback()) {
				tipoPagamento = (TipoPagamentoEnderecoEmpresa) getRequestAttribute("tipoPagamento");
				if (tipoPagamento != null) {
					codigoTipoPagamento = tipoPagamento.getTipoPagamento().getCodigo();
				}
				return;
			}
			String codTipoPag = getRequestParam("codTipoPagamento");
			if (codTipoPag != null && !codTipoPag.isEmpty()) {
				tipoPagamento = CadastroBO.getInstance().obterTipoPagamentoEmpresa(Integer.valueOf(codTipoPag));
			} else {
				tipoPagamento = new TipoPagamentoEnderecoEmpresa();
			}
			listaTiposPagamentoEmpresa = CadastroBO.getInstance().obterTiposPagamentoEmpresa();
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public void salvarTipoPagamento() {
		try {
			if (tipoPagamento != null) {
				TipoPagamento tpPagamento = CadastroBO.getInstance().obterTipoPagamento(codigoTipoPagamento);
				tipoPagamento.setTipoPagamento(tpPagamento);
				tipoPagamento = CadastroBO.getInstance().salvarTipoPagamento(tipoPagamento);
				addMsg(FacesMessage.SEVERITY_INFO, "Tipo de pagamento salvo com sucesso.");
			}
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public void desativarTipoPagamento() {
		try {
			if (tipoPagamento != null) {
				CadastroBO.getInstance().desativarTipoPagamento(tipoPagamento.getCodigo());
				addMsg(FacesMessage.SEVERITY_INFO, "Tipo de pagamento desativado com sucesso.");
			}
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public List<TipoPagamentoEnderecoEmpresa> getListaTiposPagamentoEmpresa() {
		return listaTiposPagamentoEmpresa;
	}

	public void setListaTiposPagamentoEmpresa(List<TipoPagamentoEnderecoEmpresa> listaTiposPagamentoEmpresa) {
		this.listaTiposPagamentoEmpresa = listaTiposPagamentoEmpresa;
	}

	public List<TipoPagamento> getListaTiposPagamento() {
		return listaTiposPagamento;
	}

	public void setListaTiposPagamento(List<TipoPagamento> listaTiposPagamento) {
		this.listaTiposPagamento = listaTiposPagamento;
	}

	public TipoPagamentoEnderecoEmpresa getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamentoEnderecoEmpresa tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getCodigoTipoPagamento() {
		return codigoTipoPagamento;
	}

	public void setCodigoTipoPagamento(Integer codigoTipoPagamento) {
		this.codigoTipoPagamento = codigoTipoPagamento;
	}

	public TipoPagamentoEnderecoEmpresa getTipoPagamentoSelecionado() {
		return tipoPagamentoSelecionado;
	}

	public void setTipoPagamentoSelecionado(TipoPagamentoEnderecoEmpresa tipoPagamentoSelecionado) {
		this.tipoPagamentoSelecionado = tipoPagamentoSelecionado;
	}

	@Override
	public String actionNovo() {
		tipoPagamento = new TipoPagamentoEnderecoEmpresa();
		return "cadastrarTipoPagamento.ido?faces-redirect=true";
	}

	@Override
	public String actionSalvar() {
		salvarTipoPagamento();
		return "consultarTipoPagamento.ido?faces-redirect=true";
	}

	@Override
	public String actionAlterar() {
		try {
			if (tipoPagamentoSelecionado == null) {
				addMsg(FacesMessage.SEVERITY_ERROR, "Nenhum tipo de pagamento selecionado.");
				return null;
			}
			getRequest().setAttribute("tipoPagamento", tipoPagamentoSelecionado);
			return "cadastrarTipoPagamento.ido";
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
		return null;
	}

	@Override
	public String actionExcluir() {
		try {
			if (tipoPagamentoSelecionado == null) {
				addMsg(FacesMessage.SEVERITY_ERROR, "Nenhum tipo de pagamento selecionado.");
				return null;
			} else {
				CadastroBO.getInstance().excluirTipoPagamento(tipoPagamentoSelecionado.getCodigo());
				listaTiposPagamentoEmpresa = CadastroBO.getInstance().obterTiposPagamentoEmpresa();
				addMsg(FacesMessage.SEVERITY_INFO, "Tipo de pagamento excluído com sucesso.");
			}
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
		return null;
	}

	@Override
	public String actionVoltar() {
		return "consultarTipoPagamento.ido?faces-redirect=true";
	}
}*/