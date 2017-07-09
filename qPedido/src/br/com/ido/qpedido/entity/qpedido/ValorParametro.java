package br.com.ido.qpedido.entity.qpedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.minhaLib.dao.Entidade;




@Entity
@Table(name = ValorParametro.nomeTabela, schema = ValorParametro.esquema, catalog = "projeto")
@NamedQueries(value = {
		@NamedQuery(name = "ValorParametro.listarValorParametroPelaChave", query = "select v from ValorParametro v"
				+ " where v.parametro.chave = :chave" + " order by v.codigo") })
public class ValorParametro extends Entidade {

	public final static String esquema = "projeto";

	public final static String nomeTabela = "valor_parametro";

	private static final long serialVersionUID = -3450117838551694587L;

	/**
	 * <b>Descrição:</b> Chave primária gerada automaticamente pelo Banco de
	 * Dados
	 */
	@Id
	@Column(name = "cod_valor_parametro", nullable = false)
	private Integer codigo;

	/**
	 * <b>Descrição:</b> Parametro vinculado aos valores
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_parametro", nullable = false)
	private Parametro parametro;

	/**
	 * <b>Descrição:</b> Valor do parâmetro
	 */
	@Column(name = "vl_parametro", nullable = false)
	private String valor;

	/**
	 * <b>Descrição:</b> Seta a chave primária da tabela
	 * 
	 * @param codigo
	 *            Um objeto do tipo int
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * <b>Descrição:</b> Retorna a chave primária da tabela
	 * 
	 * @return Um objeto do tipo int
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * <b>Descrição:</b> Seta o Parametro do valor
	 * 
	 * @param parametro
	 *            Um objeto do tipo Parametro
	 * @see Parametro
	 */
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	/**
	 * <b>Descrição:</b> Retorna o Parametro do valor
	 * 
	 * @return Um objeto do tipo Parametro
	 * @see Parametro
	 */
	public Parametro getParametro() {
		return parametro;
	}

	/**
	 * <b>Descrição:</b> Seta o valor da parâmetro
	 * 
	 * @param valor
	 *            Um objeto do tipo String
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * <b>Descrição:</b> Retorna o valor do parâmetro
	 * 
	 * @return Um objeto do tipo String
	 */
	public String getValor() {
		return valor;
	}

	@Override
	public Integer getIdentificador() {
		return codigo;
	}

	@Override
	public String toString() {
		return "ValorParametro [codigo=" + codigo + ", parametro=" + (parametro != null ? parametro.getChave() : "null")
				+ ", valor=" + valor + "]";
	}

}
