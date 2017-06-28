package br.com.ido.qpedido.entity.qpedido;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ido.dao.Entidade;


@Entity
@Table(name = Perfil.nomeTabela, schema = Perfil.esquema, catalog = "projeto")
public class Perfil extends Entidade{


	private static final long serialVersionUID = -6141229274298186519L;

	public final static String esquema = "projeto";
	public final static String nomeTabela = "perfil";
	
	@Id
	@Column(name = "cod_perfil", nullable = false)
	@SequenceGenerator(name = "perfil_cod_perfil_seq", sequenceName = "projeto.perfil_cod_perfil_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_cod_perfil_seq")
	private Integer codigo;

	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;


	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;
	
	@Override
	public Serializable getIdentificador() {
		return this.codigo;
	}

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
	
	

}
