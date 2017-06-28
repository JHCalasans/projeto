package br.com.ido.qpedido.mbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.bo.EmpresaBO;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;
import br.com.ido.qpedido.util.ExcecoesUtil;
import br.com.ido.qpedido.util.FacesUtil;

@ManagedBean(name = EmpresaBean.NOME_BEAN)
@ViewScoped
public class EmpresaBean extends SimpleController {

	private static final long serialVersionUID = -1951220705890260928L;

	public static final String NOME_BEAN = "empresaBean";

	private List<EnderecoEmpresa> listaEnderecoEmpresa;

	private EnderecoEmpresa enderecoEmpresa;

	@PostConstruct
	public void carregar() {
		try {
			if (getFacesContext().isPostback()) {
				return;
			}
			//enderecoEmpresa = EmpresaBO.getInstance().obterEnderecoEmpresa(FacesUtil.getCodEnderecoEmpresa());
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	@Override
	public String actionNovo() {
		return null;
	}

	@Override
	public String actionSalvar() {
		try {
			if (enderecoEmpresa != null) {
				EmpresaBO.getInstance().salvarEmpresa(enderecoEmpresa.getEmpresa());
				enderecoEmpresa = EmpresaBO.getInstance().salvarEnderecoEmpresa(enderecoEmpresa);
				addMsg(FacesMessage.SEVERITY_INFO, "Dados da empresa atualizados com sucesso.");
			}
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
		return "cadastrarEmpresa.ido?faces-redirect=true";
	}

	@Override
	public String actionAlterar() {
		return null;
	}

	@Override
	public String actionExcluir() {
		return null;
	}

	@Override
	public String actionVoltar() {
		return null;
	}

	public List<EnderecoEmpresa> getListaEnderecoEmpresa() {
		return listaEnderecoEmpresa;
	}

	public void setListaEnderecoEmpresa(List<EnderecoEmpresa> listaEnderecoEmpresa) {
		this.listaEnderecoEmpresa = listaEnderecoEmpresa;
	}

	public EnderecoEmpresa getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

}