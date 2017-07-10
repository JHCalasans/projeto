package br.com.ido.mesirva.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Relatorio.java
 * 
 * A classe Relatorio deve ser utilizada para emissão de relatórios. Após a
 * criação de algum relatório é possível exibí-lo na tela, exportá-lo para um
 * arquivo pdf/html ou enviá-lo direto para impressão.
 * 
 * A classe manipula relatórios desenvolvidos utilizando-se a ferramenta iReport
 * e utiliza a ferramenta JasperReports para emissão dos relatórios
 * 
 */
@SuppressWarnings("deprecation")
public class Relatorio {

	/** Representa o relatório gerado. */
	private JasperPrint jasperPrint_;

	/**
	 * Cria um novo Relatorio.
	 * 
	 * @param conn
	 *            Conexão com o banco de dados.
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 */
	/*
	 * public Relatorio(Connection conn, Map<String, Object> parameters, URL
	 * localRelatorio) throws JRException { try { // O objeto JasperReport
	 * representa o objeto JasperDesign (arquivo // .jrxml) compilado. // Ou
	 * seja, o arquivo .jasper JasperReport jr = (JasperReport) JRLoader
	 * .loadObject(localRelatorio); // JasperPrint representa o relatório
	 * gerado. // é criado um JasperPrint a partir de um JasperReport, contendo
	 * o // relatório preenchido. this.jasperPrint_ =
	 * JasperFillManager.fillReport(jr, parameters, conn); } catch (JRException
	 * e) { throw e; } }
	 */

	/**
	 * Cria um novo Relatorio.
	 * 
	 * @param conn
	 *            Conexão com o banco de dados.
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 */
	/*
	 * @SuppressWarnings("deprecation") public Relatorio(Connection conn,
	 * Map<String, Object> parameters, String localRelatorio) throws JRException
	 * { try { // O objeto JasperReport representa o objeto JasperDesign
	 * (arquivo // .jrxml) compilado. // Ou seja, o arquivo .jasper JasperReport
	 * jr = (JasperReport) JRLoader .loadObject(localRelatorio); // JasperPrint
	 * representa o relatório gerado. // é criado um JasperPrint a partir de um
	 * JasperReport, contendo o // relatório preenchido. this.jasperPrint_ =
	 * JasperFillManager.fillReport(jr, parameters, conn); } catch (JRException
	 * e) { throw e; } }
	 */

	/**
	 * Cria um novo Relatorio.
	 * 
	 * @param conn
	 *            Conexão com o banco de dados.
	 * @param sql
	 *            Expressão SQL (SELECT...) a ser utilizada para preenchimento
	 *            do relatório
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 * @throws SQLException
	 *             Caso exista alguma divergência ou problema com a Expressão
	 *             SQL passada como parâmetro, uma exceção é gerada.
	 */
	/*
	 * public Relatorio(Conexao conn, String sql, HashMap parameters, URL
	 * localRelatorio) throws SQLException, JRException { try { // O objeto
	 * JasperReport representa o objeto JasperDesign (arquivo // .jrxml)
	 * compilado. // Ou seja, o arquivo .jasper JasperReport jr = (JasperReport)
	 * JRLoader .loadObject(localRelatorio); // Resultado da consulta ResultSet
	 * rs = conn.executeQuery(sql); // JRResultSetDataSource é uma implementaçao
	 * de JRDataSource, o qual // é requerido // como parametro para preencher o
	 * relatório criado. // Ele armazena o dados do ResultSet
	 * JRResultSetDataSource jrRS = new JRResultSetDataSource(rs); //
	 * JasperPrint representa o relatório gerado. // é criado um JasperPrint a
	 * partir de um JasperReport, contendo o // relatório preenchido.
	 * this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrRS);
	 * rs.close(); } catch (SQLException e) { throw e; } catch (JRException e) {
	 * throw e; } }
	 */

	/**
	 * Cria um novo Relatorio.
	 * 
	 * @param conn
	 *            Conexão com o banco de dados.
	 * @param sql
	 *            Expressão SQL (SELECT...) a ser utilizada para preenchimento
	 *            do relatório
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 * @throws SQLException
	 *             Caso exista alguma divergência ou problema com a Expressão
	 *             SQL passada como parâmetro, uma exceção é gerada.
	 */
	/*
	 * public Relatorio(Conexao conn, String sql, HashMap parameters, String
	 * localRelatorio) throws SQLException, JRException { try { // O objeto
	 * JasperReport representa o objeto JasperDesign (arquivo // .jrxml)
	 * compilado. // Ou seja, o arquivo .jasper JasperReport jr = (JasperReport)
	 * JRLoader .loadObject(localRelatorio); // Resultado da consulta ResultSet
	 * rs = conn.executeQuery(sql); // JRResultSetDataSource é uma implementaçao
	 * de JRDataSource, o qual // é requerido // como parametro para preencher o
	 * relatório criado. // Ele armazena o dados do ResultSet
	 * JRResultSetDataSource jrRS = new JRResultSetDataSource(rs); //
	 * JasperPrint representa o relatório gerado. // é criado um JasperPrint a
	 * partir de um JasperReport, contendo o // relatório preenchido.
	 * this.jasperPrint_ = JasperFillManager.fillReport(jr, parameters, jrRS);
	 * rs.close(); } catch (SQLException e) { throw e; } catch (JRException e) {
	 * throw e; } }
	 */

	/**
	 * Cria um novo Relatorio.
	 * 
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 */
	public Relatorio(Map<String, Object> parameters, URL localRelatorio)
			throws JRException {
		try {
			// O objeto JasperReport representa o objeto JasperDesign (arquivo
			// .jrxml) compilado.
			// Ou seja, o arquivo .jasper
			JasperReport jr = (JasperReport) JRLoader
					.loadObject(localRelatorio);

			// JREmptyDataSource é uma implementaçao de JRDataSource, o qual é
			// requerido como parametro para preencher o relatório criado.
			// Ele armazena o dados do ResultSet, que, neste caso, é vazio
			JREmptyDataSource jrEDS = new JREmptyDataSource();

			this.setJasperPrint_(JasperFillManager.fillReport(jr, parameters,
					jrEDS));
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * Cria um novo Relatorio
	 * 
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 */
	public Relatorio(Map<String, Object> parameters, String localRelatorio)
			throws JRException {
		try {
			// O objeto JasperReport representa o objeto JasperDesign (arquivo
			// .jrxml) compilado.
			// Ou seja, o arquivo .jasper
			/*
			 * @SuppressWarnings("deprecation") JasperReport jr = (JasperReport)
			 * JRLoader .loadObject(localRelatorio);
			 */

			// JREmptyDataSource é uma implementaçao de JRDataSource, o qual é
			// requerido
			// como parametro para preencher o relatório criado.
			// Ele armazena o dados do ResultSet, que, neste caso, é vazio
			JREmptyDataSource jrEDS = new JREmptyDataSource();

			this.setJasperPrint_(JasperFillManager.fillReport(localRelatorio,
					parameters, jrEDS));
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * Cria um novo Relatorio
	 * 
	 * @param parameters
	 *            Parâmetros a serem exibidos no relatório.
	 * @param localRelatorio
	 *            Localização do relatório.
	 * @param dataSource
	 *            O dataSource a ser utilizado pelo Report, do tipo
	 *            JRBeanCollectionDataSource ou JRDataSource
	 * @throws JRException
	 *             Caso o relatório não seja encontrado ou haja algum problema
	 *             com ele, uma exceção é gerada.
	 */
	public Relatorio(Map<String, Object> parameters, URL localRelatorio,
			JRDataSource dataSource) throws JRException {
		try {
			JasperReport jr = (JasperReport) JRLoader
					.loadObject(localRelatorio);

			this.setJasperPrint_(JasperFillManager.fillReport(jr, parameters,
					dataSource));
		} catch (JRException e) {
			throw e;
		}
	}

	public Relatorio(Map<String, Object> parameters, String localRelatorio,
			JRDataSource dataSource) throws JRException {
		try {
			this.setJasperPrint_(JasperFillManager.fillReport(localRelatorio,
					parameters, dataSource));
		} catch (JRException e) {
			throw e;
		}
	}

	public Relatorio(Map<String, Object> parameters, URL localRelatorio,
			Connection conn) throws JRException {
		try {
			JasperReport jr = (JasperReport) JRLoader
					.loadObject(localRelatorio);

			this.setJasperPrint_(JasperFillManager.fillReport(jr, parameters,
					conn));
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * Exibe o relatório na tela. Usar apenas para testar o relatório
	 * localmente, pois não irá funcionar nos servidores.
	 */
	@Deprecated
	public void exibirRelatorio() {
		// false indica que a aplicação não será finalizada caso o relatório
		// seja fechado
		JasperViewer.viewReport(this.getJasperPrint_(), false);
	}

	/**
	 * Grava o relatório em um arquivo de formato pdf.
	 * 
	 * @param caminhoDestino
	 *            Caminho onde o arquivo será gravado.
	 */
	public void exportaParaArquivoPdf(String caminhoDestino) throws JRException {
		try {
			// Gera o arquivo PDF
			JasperExportManager.exportReportToPdfFile(this.getJasperPrint_(),
					caminhoDestino);
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * Grava o relatório em um arquivo de formato html.
	 * 
	 * @param caminhoDestino
	 *            Caminho onde o arquivo será gravado.
	 */
	public void exportaParaArquivoHtml(String caminhoDestino)
			throws JRException {
		try {
			// Gera o arquivo HTML
			JasperExportManager.exportReportToHtmlFile(this.getJasperPrint_(),
					caminhoDestino);
		} catch (JRException e) {
			throw e;
		}
	}
	
	public String exportToHtml() {
		String result = null;

		try {
			JRHtmlExporter htmlExporter = new JRHtmlExporter();

			// setup the exporter
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, this.getJasperPrint_());
			OutputStream os = new ByteArrayOutputStream();
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);

			// do the work
			htmlExporter.exportReport();

			result = os.toString();
		} catch (JRException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Retorna o relatório em byte[], representando o PDF.
	 */
	public byte[] exportaParaPDF() throws JRException {
		try {
			return JasperExportManager.exportReportToPdf(this.getJasperPrint_());
		} catch (JRException e) {
			throw e;
		}
	}

	/**
	 * Envia o relatório para impressão, exibindo uma caixa de dialogo de
	 * impressão ou não.
	 * 
	 * @param exibeCaixaDialogo
	 *            Boolean indicando se será exibida uma caixa de diálogo ou não.
	 */
	public void imprimir(boolean exibeCaixaDialogo) throws JRException {
		try {
			// Imprime o relatório
			// o segundo parâmetro indica se existirá uma caixa de dialogo antes
			// ou nao
			JasperPrintManager
					.printReport(this.getJasperPrint_(), exibeCaixaDialogo);
		} catch (JRException e) {
			throw e;
		}
	}

	public JasperPrint getJasperPrint_() {
		return jasperPrint_;
	}

	public void setJasperPrint_(JasperPrint jasperPrint_) {
		this.jasperPrint_ = jasperPrint_;
	}
}
