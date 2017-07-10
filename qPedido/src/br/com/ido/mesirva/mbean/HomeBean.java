package br.com.ido.mesirva.mbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.com.ido.mesirva.util.ExcecoesUtil;

@ManagedBean(name = HomeBean.NOME_BEAN)
@ViewScoped
public class HomeBean extends SimpleController{


	private static final long serialVersionUID = -2819017417735069396L;
	
	public static final String NOME_BEAN = "homeBean";
	
	private DefaultSubMenu subMenu;
	
	private MenuModel model;
	
	@PostConstruct
	public void carregar() {
		try {
			setSubMenu(new DefaultSubMenu("Teste"));
			getSubMenu().setStyle("colo");;
			model = new DefaultMenuModel();
			model.addElement(subMenu);
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}
	

	@Override
	public String actionNovo() {
		System.out.println("entrou");
		return "Opaaaa";
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


	public DefaultSubMenu getSubMenu() {
		return subMenu;
	}


	public void setSubMenu(DefaultSubMenu subMenu) {
		this.subMenu = subMenu;
	}


	public MenuModel getModel() {
		return model;
	}


	public void setModel(MenuModel model) {
		this.model = model;
	}


	

}
