package br.com.ido.qpedido.entity.qpedido;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.ido.dao.Entidade;



@Entity
@Table(name = TipoPagamento.nomeTabela, schema = TipoPagamento.esquema, catalog = "projeto")
@NamedQueries(value = {
		@NamedQuery(name = "TipoPagamento.obterTiposPagamentoAtivos", query = "select t from TipoPagamento t "
				+ "where t.ativo = true")

})
public class TipoPagamento extends Entidade {

	public final static String esquema = "projeto";
	public final static String nomeTabela = "tipo_pagamento";
	private static final long serialVersionUID = -3450117838551694587L;

	@Id
	@Column(name = "cod_tipo_pagamento", nullable = false)
	private Integer codigo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public Integer getIdentificador() {
		return codigo;
	}

}
