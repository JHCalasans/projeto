package br.com.ido.qpedido.dao.impl.postgres;

import org.apache.log4j.Logger;

import br.com.ido.qpedido.dao.FabricaDAO;
import br.com.ido.qpedido.dao.IEmpresaDAO;
import br.com.ido.qpedido.dao.IEnderecoEmpresaDAO;
import br.com.ido.qpedido.dao.ITipoPagamentoDAO;
import br.com.ido.qpedido.dao.ITipoPagamentoEnderecoEmpresaDAO;
import br.com.ido.qpedido.dao.IUsuarioDAO;

final class PostgresFabricaDAO extends FabricaDAO {

	private static Logger log = Logger.getLogger(PostgresFabricaDAO.class);
	static PostgresFabricaDAO instance;

	static {
		log.info("Instanciando e registrando como fábrica principal.");
		instance = new PostgresFabricaDAO();
		instance.registerAsMain();
	}

	private static PostgresGenericDAOImplBO postgresGenericDAOImplBO;
	private static PostgresTipoPagamentoEnderecoEmpresaDAOImpl postgresTipoPagamentoEnderecoEmpresaDAOImpl;
	private static PostgresTipoPagamentoDAOImpl postgresTipoPagamentoDAOImpl;
	private static PostgresEnderecoEmpresaDAOImpl postgresEnderecoEmpresaDAOImpl;
	private static PostgresEmpresaDAOImpl postgresEmpresaDAOImpl;
	private static PostgresUsuarioDAOImpl postgresUsuarioDAOImpl;

	private PostgresFabricaDAO() {

	}

	public static void registerMeAsMain() {
		getInstance().registerAsMain();
	}

	protected static PostgresFabricaDAO getInstance() {
		return instance;
	}

	protected static PostgresFabricaDAO getNewInstance() {
		return new PostgresFabricaDAO();
	}

	public PostgresGenericDAOImplBO getGenericDAOImplBO() {
		if (postgresGenericDAOImplBO == null) {
			postgresGenericDAOImplBO = new PostgresGenericDAOImplBO();
		}
		return postgresGenericDAOImplBO;
	}

	public ITipoPagamentoEnderecoEmpresaDAO getPostgresTipoPagamentoEnderecoEmpresaDAO() {
		if (postgresTipoPagamentoEnderecoEmpresaDAOImpl == null) {
			postgresTipoPagamentoEnderecoEmpresaDAOImpl = new PostgresTipoPagamentoEnderecoEmpresaDAOImpl();
		}
		return postgresTipoPagamentoEnderecoEmpresaDAOImpl;
	}

	public ITipoPagamentoDAO getPostgresTipoPagamentoDAO() {
		if (postgresTipoPagamentoDAOImpl == null) {
			postgresTipoPagamentoDAOImpl = new PostgresTipoPagamentoDAOImpl();
		}
		return postgresTipoPagamentoDAOImpl;
	}

	public IEnderecoEmpresaDAO getPostgresEnderecoEmpresaDAO() {
		if (postgresEnderecoEmpresaDAOImpl == null) {
			postgresEnderecoEmpresaDAOImpl = new PostgresEnderecoEmpresaDAOImpl();
		}
		return postgresEnderecoEmpresaDAOImpl;
	}

	public IEmpresaDAO getPostgresEmpresaDAO() {
		if (postgresEmpresaDAOImpl == null) {
			postgresEmpresaDAOImpl = new PostgresEmpresaDAOImpl();
		}
		return postgresEmpresaDAOImpl;
	}	

	public IUsuarioDAO getPostgresUsuarioDAO() {
		if (postgresUsuarioDAOImpl == null) {
			postgresUsuarioDAOImpl = new PostgresUsuarioDAOImpl();
		}
		return postgresUsuarioDAOImpl;
	}
}
