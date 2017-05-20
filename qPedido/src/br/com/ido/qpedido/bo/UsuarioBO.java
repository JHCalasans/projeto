package br.com.ido.qpedido.bo;

public class UsuarioBO extends BaseBO {

	private static UsuarioBO instance;

	private UsuarioBO() {

	}

	public static UsuarioBO getInstance() {
		if (instance == null)
			instance = new UsuarioBO();

		return instance;
	}

}
