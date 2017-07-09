package br.com.ido.qpedido.mbean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import br.com.ido.qpedido.bo.MesaEnderecoEmpresaBO;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.ido.qpedido.util.ExcecoesUtil;
import br.com.ido.qpedido.util.FacesUtil;
import br.com.ido.qpedido.util.QRCodeUtil;
import br.com.ido.qpedido.util.UtilDownload;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;

@ManagedBean(name = MesaBean.NOME_BEAN)
@ViewScoped
public class MesaBean extends SimpleController {

	private static final long serialVersionUID = -7985703228564386989L;

	public static final String NOME_BEAN = "mesaBean";

	private List<MesaEnderecoEmpresa> listaMesas;

	private MesaEnderecoEmpresa mesa;

	private StreamedContent qrCodeImg;

	@PostConstruct
	public void carregar() {
		try {
			String codMesa = getRequestParam("codMesa");
			if (codMesa != null)
				mesa = MesaEnderecoEmpresaBO.getInstance().obterPorCod(Long.valueOf(codMesa));
			else
				mesa = new MesaEnderecoEmpresa();
			if (getFacesContext().isPostback()) {
				return;
			}
			setListaMesas(MesaEnderecoEmpresaBO.getInstance()
					.obterMesasPorEnderecoEmpresa(getUsuarioLogado().getEnderecoEmpresa().getCodigo()));
		} catch (Exception e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public void downloadQRCode() {
		try {
			UtilDownload.download(QRCodeUtil.gerarQRCode(), "QRCode Mesa " + mesa.getNumero() + ".jpg",
					UtilDownload.MIMETYPE_OCTETSTREAM, UtilDownload.CONTENT_DISPOSITION_ATTACHMENT);
		} catch (IOException | ExcecaoNegocio e) {
			ExcecoesUtil.TratarExcecao(e);
		}
	}

	public void irParaAlterar() {
		FacesUtil.redirecionar(null, "/paginas/mesa/alterarMesa.xhtml", true, null);
	}

	@Override
	public String actionNovo() {
		mesa = new MesaEnderecoEmpresa();
		return "cadastrarMesa.ido?faces-redirect=true";
	}

	@Override
	public String actionSalvar() {

		try {
			mesa.setEnderecoEmpresa(getUsuarioLogado().getEnderecoEmpresa());			
			mesa = MesaEnderecoEmpresaBO.getInstance().salvarMesa(getMesa());
			addMsg(FacesMessage.SEVERITY_INFO, "Mesa inserida com sucesso.!");
		} catch (ExcecaoNegocio e) {
			ExcecoesUtil.TratarExcecao(e);
		}
		return "";
	}

	@Override
	public String actionAlterar() {
		try {
			mesa = MesaEnderecoEmpresaBO.getInstance().alterarMesa(getMesa(), getUsuarioLogado().getEnderecoEmpresa());
			addMsg(FacesMessage.SEVERITY_INFO, "Mesa alterada com sucesso.!");
		} catch (ExcecaoNegocio e) {
			ExcecoesUtil.TratarExcecao(e);
		}

		return "";
	}

	@Override
	public String actionExcluir() {
		try {
			MesaEnderecoEmpresaBO.getInstance().excluirMesa(mesa);
			mesa = null;
			addMsg(FacesMessage.SEVERITY_INFO, "Mesa Excluída com sucesso.!");
		} catch (ExcecaoNegocio e) {
			e.printStackTrace();
		}
		return "consultarMesa.ido?faces-redirect=true";
	}

	@Override
	public String actionVoltar() {
		return "consultarMesa.ido?faces-redirect=true";
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

	public StreamedContent getQrCodeImg() {
		return qrCodeImg;
	}

	public void setQrCodeImg(StreamedContent qrCodeImg) {
		this.qrCodeImg = qrCodeImg;
	}

}
