package br.com.ido.qpedido.dao;

public abstract class FabricaDAO {

	private static FabricaDAO instance;

	public abstract ITipoPagamentoEnderecoEmpresaDAO getPostgresTipoPagamentoEnderecoEmpresaDAO();

	public abstract ITipoPagamentoDAO getPostgresTipoPagamentoDAO();

	public abstract IEnderecoEmpresaDAO getPostgresEnderecoEmpresaDAO();
	
	public abstract IEmpresaDAO getPostgresEmpresaDAO();
	
	public abstract IUsuarioDAO getPostgresUsuarioDAO();

	/**
	 * Para utilizar de fato a classe {@link FabricaDAO}, uma implementação
	 * sua deve se registrar como sendo a instância principal usando
	 * {@code instancia.registerAsMain()}.
	 */
	protected final void registerAsMain() {
		FabricaDAO.instance = this;
	}

	public static FabricaDAO getFabricaDAO() {
		return instance;
	}

}
