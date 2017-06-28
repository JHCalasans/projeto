package br.com.ido.qpedido.entity.qpedido;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = UsuarioEnderecoEmpresa.nomeTabela, schema = UsuarioEnderecoEmpresa.esquema, catalog = "projeto")
@NamedQueries(value = {
		@NamedQuery(name = "UsuarioEnderecoEmpresa.obterPorUsuario", query = "select ue from UsuarioEnderecoEmpresa ue join fetch ue.usuario u"
				+ " where u.codigo = :codUsuario") })
public class UsuarioEnderecoEmpresa extends Entidade{


	private static final long serialVersionUID = 6274950657700859965L;
	
	public final static String esquema = "projeto";
	public final static String nomeTabela = "usuario_endereco_empresa";
	
	@Id
	@Column(name = "cod_usuario_endereco_empresa", nullable = false)
	@SequenceGenerator(name = "usuario_endereco_empresa_cod_usuario_endereco_empresa_seq", sequenceName = "projeto.usuario_endereco_empresa_cod_usuario_endereco_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_endereco_empresa_cod_usuario_endereco_empresa_seq")
	private Integer codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_usuario", nullable = false, referencedColumnName = "cod_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco_empresa", nullable = false, referencedColumnName = "cod_endereco_empresa")
	private EnderecoEmpresa enderecoEmpresa;
	
	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;
	
	@Column(name = "dt_desativacao", nullable = true)
	private Date dataDesativacao;
	

	@Override
	public Serializable getIdentificador() {
		
		return getCodigo();
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public EnderecoEmpresa getEnderecoEmpresa() {
		return enderecoEmpresa;
	}


	public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
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


	public Date getDataDesativacao() {
		return dataDesativacao;
	}


	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

}
