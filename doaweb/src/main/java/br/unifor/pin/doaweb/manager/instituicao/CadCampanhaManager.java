package br.unifor.pin.doaweb.manager.instituicao;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.CampanhaBO;
import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.TipoDoacao;
import br.unifor.pin.doaweb.to.SegurancaTO;
import br.unifor.pin.doaweb.utils.MessagesUtils;
import br.unifor.pin.doaweb.utils.Navigation;

@RequestScoped
@ManagedBean(name = "cadCampanha")
@Component(value = "cadCampanha")
public class CadCampanhaManager {

	@Autowired
	private CampanhaBO campanhaBO;
	@Autowired
	private SegurancaTO segurancaTO;

	private Date dataInicioCampanhas;
	private Date dataTerminoCampanhas;
	private TipoDoacao tipo;
	private String descricao;
	private StatusCampanha statusCampanha;
	private Instituicoes instituicao;

	public String preparaSalvar() {
		this.limpaDados();
		this.dataInicioCampanhas = new Date();
		return Navigation.CADASTROCAMP;
	}

	public String salvar() {
		Campanhas campanha = new Campanhas();
		campanha.setDataInicioCampanhas(new Date());
		campanha.setDataTerminoCampanhas(getDataTerminoCampanhas());
		campanha.setDescricao(getDescricao());
		campanha.setInstituicao((Instituicoes) segurancaTO.getUsuario());
		campanha.setTipo(getTipo());
		campanha.setStatus(StatusCampanha.ATIVA);

		if (validarDataFinal(getDataTerminoCampanhas()) == true) {

			try {
				this.campanhaBO.salvar(campanha);
				MessagesUtils.info("Campanha cadastrada com sucesso!");
				preparaSalvar();
			} catch (Exception e) {
				MessagesUtils.error(e.getMessage());
			}

		} else {
			MessagesUtils
					.error("Data de ENCERRAMENTO deve ser posterior a data ATUAL, insira uma data correta!");
		}
		
		return Navigation.SUCESSO;

	}

	// Verifica a data final no cadastro da campanha
	public boolean validarDataFinal(Date date) {
		return campanhaBO.validaDataDeTerminoDeCampanha(date);
	}

	public void limpaDados() {
		this.dataTerminoCampanhas = null;
		this.descricao = "";
	}

	// Metodo para pegar os labels do Enum TipoDeDoacao
	public TipoDoacao[] getTipoDoacaoValores() {
		return TipoDoacao.values();
	}

	/*
	 * GETTS AND SETTS
	 */

	public CampanhaBO getCampanhaBO() {
		return campanhaBO;
	}

	public void setCampanhaBO(CampanhaBO campanhaBO) {
		this.campanhaBO = campanhaBO;
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

	public StatusCampanha getStatusCampanha() {
		return statusCampanha;
	}

	public void setStatusCampanha(StatusCampanha statusCampanha) {
		this.statusCampanha = statusCampanha;
	}

}
