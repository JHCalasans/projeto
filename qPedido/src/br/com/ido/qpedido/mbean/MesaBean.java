package br.com.ido.qpedido.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ido.qpedido.bo.MesaEnderecoEmpresaBO;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.ido.qpedido.util.ExcecoesUtil;

@ManagedBean(name = MesaBean.NOME_BEAN)
@ViewScoped
public class MesaBean extends SimpleController{

	private static final long serialVersionUID = -7985703228564386989L;
	
	public static final String NOME_BEAN = "mesaBean";
	
	private List<MesaEnderecoEmpresa> listaMesas;
	
	private MesaEnderecoEmpresa mesa;

	@PostConstruct
	public void carregar() {
		try {
			if (getFacesContext().isPostback()) {
				return;
			}
			setListaMesas(MesaEnderecoEmpresaBO.getInstance().obterMesasPorEnderecoEmpresa(1));
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
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


	public List<MesaEnderecoEmpresa> getListaMesas() {
		return listaMesas;
	}


	public void setListaMesas(List<MesaEnderecoEmpresa> listaMesas) {
		this.listaMesas = listaMesas;
	}


	public MesaEnderecoEmpresa getMesa() {
		return mesa;
	}


	public void setMesa(MesaEnderecoEmpresa mesa) {
		this.mesa = mesa;
	}

}
