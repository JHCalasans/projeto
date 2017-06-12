package br.com.ido.qpedido.entity.qpedido;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ido.dao.Entidade;

@Entity
@Table(name = Usuario.nomeTabela, schema = Usuario.esquema, catalog = "projeto")
@XmlRootElement
public class Usuario extends Entidade{

	
	private static final long serialVersionUID = -2417242465986240687L;
	
	public final static String esquema = "projeto";
	public final static String nomeTabela = "usuario";
	
	@Id
	@Column(name = "cod_usuario", nullable = false)
	@SequenceGenerator(name = "usuario_cod_usuario_seq", sequenceName = "projeto.usuario_cod_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_cod_usuario_seq")
	private Integer codigo;


	@JoinColumn(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = false, length = 50)
	private String telefone;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "cpf", nullable = true)
	private String cpf;

	@Column(name = "flg_ativo", nullable = false)
	private boolean ativo;
	
	@Column(name = "dt_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "dt_nascimento", nullable = true)
	private Date dataNascimento;
	
	
	@Column(name = "img_usuario", length = 2 * 1024 * 1024, nullable = true)
	private byte[] imgUsuario;


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


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public byte[] getImgUsuario() {
		return imgUsuario;
	}


	public void setImgUsuario(byte[] imgUsuario) {
		this.imgUsuario = imgUsuario;
	}
	
	

}
