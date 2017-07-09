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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.minhaLib.dao.Entidade;




@Entity
@Table(name = UsuarioPerfil.nomeTabela, schema = UsuarioPerfil.esquema, catalog = "projeto")
public class UsuarioPerfil extends Entidade{


	private static final long serialVersionUID = -8048816827899294806L;
	

	public final static String esquema = "projeto";
	public final static String nomeTabela = "usuario_perfil";
	
	@Id
	@Column(name = "cod_usuario_perfil", nullable = false)
	@SequenceGenerator(name = "usuario_perfil_cod_usuario_perfil_seq", sequenceName = "projeto.usuario_perfil_cod_usuario_perfil_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_perfil_cod_usuario_perfil_seq")
	private Integer codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_usuario", nullable = false, referencedColumnName = "cod_usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_perfil", nullable = false, referencedColumnName = "cod_perfil")
	private Perfil perfil;
	
	@Column(name = "dt_inicio", nullable = false)
	private Date dataInicio;
	
	@Column(name = "dt_final", nullable = true)
	private Date dataFinal;

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	
	

}
