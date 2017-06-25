package br.com.ido.qpedido.entity.qpedido;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.ido.dao.Entidade;

@Entity
@Table(name = EnderecoEmpresa.nomeTabela, schema = EnderecoEmpresa.esquema, catalog = "projeto")
@NamedQueries(value = {
		@NamedQuery(name = "EnderecoEmpresa.obterEnderecoFiliais", query = "select e from EnderecoEmpresa e"
				+ " where e.empresa.codigo = :codEmpresa and e.filial = true"),
		@NamedQuery(name = "EnderecoEmpresa.obterPorEstado", query = "select e from EnderecoEmpresa e join fetch e.empresa ep"
				+ " where e.estado = :sigla"),

})
public class EnderecoEmpresa extends Entidade {

	private static final long serialVersionUID = -9165454169466824008L;
	public final static String esquema = "projeto";
	public final static String nomeTabela = "endereco_empresa";

	@Id
	@Column(name = "cod_endereco_empresa", nullable = false)
	@SequenceGenerator(name = "endereco_empresa_cod_endereco_empresa_seq", sequenceName = "projeto.endereco_empresa_cod_endereco_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_empresa_cod_endereco_empresa_seq")
	private Integer codigo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cod_empresa", nullable = false, referencedColumnName = "cod_empresa")
	private Empresa empresa;

	@Column(name = "logradouro", nullable = false, length = 50)
	private String logradouro;

	@Column(name = "cidade", nullable = false, length = 50)
	private String cidade;

	@Column(name = "cep", nullable = false, length = 8)
	private String cep;

	@Column(name = "numero", nullable = false, length = 5)
	private String numero;

	@Column(name = "complemento", nullable = true, length = 50)
	private String complemento;

	@Column(name = "bairro", nullable = false, length = 50)
	private String bairro;

	@Column(name = "sigla", nullable = false, length = 2)
	private String estado;

	@Column(name = "flg_filial", nullable = false)
	private boolean filial;

	@Column(name = "link_maps", nullable = true, length = 100)
	private String linkMapa;

	@Column(name = "descricao", nullable = true, length = 100)
	private String descricao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isFilial() {
		return filial;
	}

	public void setFilial(boolean filial) {
		this.filial = filial;
	}

	public String getLinkMapa() {
		return linkMapa;
	}

	public void setLinkMapa(String linkMapa) {
		this.linkMapa = linkMapa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Integer getIdentificador() {
		return codigo;
	}

}
