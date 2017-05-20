package br.com.ido.qpedido.entity.qpedido;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ido.dao.Entidade;

@Entity
@Table(name = TipoPagamentoEnderecoEmpresa.nomeTabela, schema = TipoPagamentoEnderecoEmpresa.esquema, catalog = "maisqpedido")
@NamedQueries(value = {
		@NamedQuery(name = "TipoPagamentoEnderecoEmpresa.obterPorEnderecoEmpresa", query = "select t from TipoPagamentoEnderecoEmpresa t "
				+ "where t.enderecoEmpresa.codigo = :codEnderecoEmpresa")

})
public class TipoPagamentoEnderecoEmpresa extends Entidade {

	public final static String esquema = "maisqpedido";
	public final static String nomeTabela = "tipo_pagamento_endereco_empresa";
	private static final long serialVersionUID = -3450117838551694587L;

	@Id
	@SequenceGenerator(name = "tipo_pagamento_endereco_empresa_cod", sequenceName = "maisqpedido.tipo_pagamento_endereco_empresa_cod_tipo_pagamento_endereco_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pagamento_endereco_empresa_cod")
	@Column(name = "cod_tipo_pagamento_endereco_empresa", nullable = false)
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name = "cod_endereco_empresa", nullable = false, referencedColumnName = "cod_endereco_empresa")
	private EnderecoEmpresa enderecoEmpresa;

	@ManyToOne
	@JoinColumn(name = "cod_tipo_pagamento", nullable = false, referencedColumnName = "cod_tipo_pagamento")
	private TipoPagamento tipoPagamento;

	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;

	@Column(name = "dt_desativacao", nullable = true)
	private Date dataDesativacao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public EnderecoEmpresa getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
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
