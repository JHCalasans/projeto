package br.com.ido.qpedido.mbean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.bo.MesaEnderecoEmpresaBO;
import br.com.ido.qpedido.bo.UsuarioEnderecoEmpresaBO;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.ido.qpedido.entity.qpedido.UsuarioEnderecoEmpresa;
import br.com.ido.qpedido.util.ExcecoesUtil;
import br.com.ido.qpedido.util.FacesUtil;
import br.com.ido.qpedido.util.Paginas;

@ManagedBean(name = LoginBean.NOME_BEAN)
@ViewScoped
public class LoginBean extends SimpleController {

	private static final long serialVersionUID = 524489573749923264L;
	public static final String NOME_BEAN = "loginBean";
	
	private String login; 
	private String senha;

	private boolean exibeBannerMenu = true;
	
	
	@PostConstruct
	public void carregar() {
		try {
			if (getSessionMap().containsKey("bringme.usuarioEnderecoEmpresa"))
				FacesUtil.redirecionar(null, Paginas.PAG_HOME, true, null);

		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}
	

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
			FacesUtil.redirecionar(null, Paginas.PAG_LOGIN, true, null);
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}
	
	public void logar() {
		try {
			UsuarioEnderecoEmpresa usuarioEnderecoEmpresa = UsuarioEnderecoEmpresaBO.getInstance().obterUsuarioEnderecoEmpresaAtivoPorLoginESenha(login, senha);
			if(usuarioEnderecoEmpresa != null){
				getSessionMap().put("bringme.usuarioEnderecoEmpresa", usuarioEnderecoEmpresa);	
				FacesUtil.redirecionar(null, Paginas.PAG_HOME, true, null);
			}else
				addMsg(FacesMessage.SEVERITY_ERROR, "Login/Senha incorretos.");
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
