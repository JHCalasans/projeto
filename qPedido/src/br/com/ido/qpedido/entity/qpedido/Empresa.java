package br.com.ido.qpedido.entity.qpedido;

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
@Table(name = Empresa.nomeTabela, schema = Empresa.esquema, catalog = "projeto")
public class Empresa extends Entidade {

	private static final long serialVersionUID = -9165454169466824008L;
	public final static String esquema = "projeto";
	public final static String nomeTabela = "empresa";

	@Id
	@Column(name = "cod_empresa", nullable = false)
	@SequenceGenerator(name = "cod_empresa_seq", sequenceName = "projeto.empresa_cod_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cod_empresa_seq")
	private Integer codigo;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "cnpj", nullable = false, length = 14)
	private String cnpj;

	@Column(name = "telefone", nullable = true, length = 10)
	private String telefone;

	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;

	@Column(name = "dt_desativacao", nullable = true)
	private Date dataDesativacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	@Override
	public Integer getIdentificador() {
		return codigo;
	}

}
