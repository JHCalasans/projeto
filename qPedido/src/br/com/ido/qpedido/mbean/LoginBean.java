package br.com.ido.qpedido.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.util.ExcecoesUtil;
import br.com.ido.qpedido.util.FacesUtil;
import br.com.ido.qpedido.util.Paginas;

@ManagedBean(name = LoginBean.NOME_BEAN)
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 524489573749923264L;
	public static final String NOME_BEAN = "loginBean";

	private boolean exibeBannerMenu = true;

	public boolean isExibeBannerMenu() {
		return exibeBannerMenu;
	}

	public void setExibeBannerMenu(boolean exibeBannerMenu) {
		this.exibeBannerMenu = exibeBannerMenu;
	}

	public LoginBean() {

	}

	public void redirecionarHome() {
		try {
			FacesUtil.redirecionar(null, Paginas.PAG_HOME, true, null);
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}
}
