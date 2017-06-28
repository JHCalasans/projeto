package br.com.ido.qpedido.mbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.util.ExcecoesUtil;

@ManagedBean(name = QrcodeBean.NOME_BEAN)
@ViewScoped
public class QrcodeBean extends SimpleController{

	private static final long serialVersionUID = 6487129747915825128L;
	
	public static final String NOME_BEAN = "qrcodeBean";
	
	//public List<Mes>

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
		
		return null;
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

}
