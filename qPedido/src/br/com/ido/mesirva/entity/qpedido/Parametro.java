package br.com.ido.mesirva.entity.qpedido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.minhaLib.dao.Entidade;



@Entity
@Table(name = Parametro.nomeTabela, schema = Parametro.esquema, catalog = "projeto")
public class Parametro extends Entidade {

	public final static String esquema = "projeto";
	public final static String nomeTabela = "Parametro";
	private static final long serialVersionUID = -3450117838551694587L;

	/**
	 * <b>Descrição:</b> Chave primária gerada automaticamente pelo Banco de
	 * Dados
	 */
	@Id
	@Column(name = "cod_parametro", nullable = false)
	private Long codParametro;

	/**
	 * <b>Descrição:</b> Chave do parametro
	 */
	@Column(name = "ds_chave", nullable = false)
	private String chave;

	/**
	 * <b>Descrição:</b> Descrição do parametro
	 */
	@Column(name = "ds_parametro", nullable = false)
	private String descricao;

	/**
	 * <b>Descrição:</b> Seta a chave do parâmetro
	 * 
	 * @param chave
	 *            Um objeto do tipo String
	 */
	public void setChave(String chave) {
		this.chave = chave;
	}

	/**
	 * <b>Descrição:</b> Retorna a chave do parâmetro
	 * 
	 * @return Um objeto do tipo String
	 */
	public String getChave() {
		return chave;
	}

	/**
	 * <b>Descrição:</b> Seta a descrição do parâmetro
	 * 
	 * @param descricao
	 *            Um objeto do tipo String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * <b>Descrição:</b> Retorna a descrição parâmetro
	 * 
	 * @return Um objeto do tipo String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * <b>Descrição:</b> Seta a chave primária da tabela
	 * 
	 * @param codParametro
	 *            Um objeto do tipo int
	 */
	public void setCodParametro(Long codParametro) {
		this.codParametro = codParametro;
	}

	/**
	 * <b>Descrição:</b> Retorna a chave primária da tabela
	 * 
	 * @return Um objeto do tipo int
	 */
	public Long getCodParametro() {
		return codParametro;
	}

	@Override
	public Long getIdentificador() {
		return codParametro;
	}

	@Override
	public String toString() {
		return "Parametro [codParametro=" + codParametro + ", chave=" + chave + "]";
	}

}
