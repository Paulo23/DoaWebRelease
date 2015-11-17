package br.unifor.pin.doaweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.StatusDoacao;
import br.unifor.pin.doaweb.enums.TipoDoacao;

@Entity
public class Campanhas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_campanhas")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio_campanhas", nullable = false, updatable = false)
	private Date dataInicioCampanhas;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino_campanhas", nullable = false, updatable = false)
	private Date dataTerminoCampanhas;

	@Column(name = "tipo_campanhas", nullable = false, updatable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoDoacao tipo;
	
	@Column(name = "status_campanha")
	@Enumerated(EnumType.ORDINAL)
	private StatusCampanha status;

	@Column(name = "descricao_campanhas", nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Instituicoes instituicao;
	
	@OneToMany(mappedBy = "campanha", fetch=FetchType.EAGER)
	private List<Doacao> doacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicioCampanhas() {
		return dataInicioCampanhas;
	}

	public void setDataInicioCampanhas(Date dataInicioCampanhas) {
		this.dataInicioCampanhas = dataInicioCampanhas;
	}

	public Date getDataTerminoCampanhas() {
		return dataTerminoCampanhas;
	}

	public void setDataTerminoCampanhas(Date dataTerminoCampanhas) {
		this.dataTerminoCampanhas = dataTerminoCampanhas;
	}

	public TipoDoacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoDoacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instituicoes getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicoes instituicao) {
		this.instituicao = instituicao;
	}

	public StatusCampanha getStatus() {
		return status;
	}

	public void setStatus(StatusCampanha status) {
		this.status = status;
	}
	
	
}
