package br.com.ido.qpedido.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = MasterPageBean.NOME_BEAN)
@ViewScoped
public class MasterPageBean extends SimpleController {

	private static final long serialVersionUID = 524489573749923264L;
	public static final String NOME_BEAN = "masterPageBean";

	private boolean exibeBannerMenu = true;

	public boolean isExibeBannerMenu() {
		return exibeBannerMenu;
	}

	public void setExibeBannerMenu(boolean exibeBannerMenu) {
		this.exibeBannerMenu = exibeBannerMenu;
	}

	public MasterPageBean() {

	}

	@Override
	public String actionNovo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actionSalvar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actionAlterar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actionExcluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actionVoltar() {
		// TODO Auto-generated method stub
		return null;
	}
}
