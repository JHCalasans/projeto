package br.com.ido.qpedido.entity.qpedido;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ido.dao.Entidade;

@Entity
@Table(name = MesaEnderecoEmpresa.nomeTabela, schema = MesaEnderecoEmpresa.esquema, catalog = "projeto")
@NamedQueries(value = {
		@NamedQuery(name = "MesaEnderecoEmpresa.obterMesasPorEnderecoEmpresa", query = "select m from MesaEnderecoEmpresa m join fetch m.enderecoEmpresa e"
				+ " where e.codigo = :codEnderecoEmpresa order by m.numero asc") })
public class MesaEnderecoEmpresa extends Entidade {

	private static final long serialVersionUID = -2750705203286181482L;

	public final static String esquema = "projeto";
	public final static String nomeTabela = "mesa_endereco_empresa";

	@Id
	@Column(name = "cod_mesa_endereco_empresa", nullable = false)
	@SequenceGenerator(name = "mesa_endereco_empresa_cod_mesa_endereco_empresa_seq", sequenceName = "projeto.mesa_endereco_empresa_cod_mesa_endereco_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mesa_endereco_empresa_cod_mesa_endereco_empresa_seq")
	private Long codigo;

	@Column(name = "nr_mesa", nullable = false, length = 50)
	private Integer numero;

	
	/*@Column(name = "qr_code", length = 2 * 1024 * 1024)
	private byte[] qrcode;*/

	@Column(name = "flg_ocupada", nullable = false)
	private boolean ocupada;
	
	@Column(name = "cadeiras", nullable = false)
	private Integer qtdCadeiras;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco_empresa", nullable = false, referencedColumnName = "cod_endereco_empresa")
	private EnderecoEmpresa enderecoEmpresa;

	@Override
	public Serializable getIdentificador() {
		return getCodigo();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/*public byte[] getQrcode() {
		return qrcode;
	}

	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}*/

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public EnderecoEmpresa getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public Integer getQtdCadeiras() {
		return qtdCadeiras;
	}

	public void setQtdCadeiras(Integer qtdCadeiras) {
		this.qtdCadeiras = qtdCadeiras;
	}

}
