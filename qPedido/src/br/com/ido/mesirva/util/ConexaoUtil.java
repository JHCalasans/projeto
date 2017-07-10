package br.com.ido.mesirva.util;

import java.sql.Connection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

@ManagedBean(name = "conexaoUtil", eager = true)
@ApplicationScoped
public class ConexaoUtil {

	/**
	 * Retorna a conexão configurada em context.xml.
	 * <em>É extremamente importante lembrar de sempre fechar esta conexão ao terminar de usá-la!</em>
	 * 
	 * @return Uma conexão a banco, conforme configurada em context.xml.
	 * @throws Exception
	 *             Caso ocorra qualquer problema na instanciação da conexão, ou
	 *             ao obter o contexto.
	 */
	public static Connection getConnection() throws Exception {
		Context ctx = null;
		ctx = (Context) new InitialContext().lookup("java:comp/env");
		DataSource dataSourceDBCache = (DataSource) ctx.lookup("jdbc/cacheapp");
		return dataSourceDBCache.getConnection();
	}
}
