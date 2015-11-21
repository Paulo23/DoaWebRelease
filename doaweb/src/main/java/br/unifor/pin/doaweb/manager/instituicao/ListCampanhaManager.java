package br.unifor.pin.doaweb.manager.instituicao;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.CampanhaBO;
import br.unifor.pin.doaweb.bussines.DoacaoBO;
import br.unifor.pin.doaweb.dao.CampanhasDAO;
import br.unifor.pin.doaweb.dao.DoacaoDAO;
import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Doacao;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.StatusDoacao;
import br.unifor.pin.doaweb.enums.TipoDoacao;
import br.unifor.pin.doaweb.to.SegurancaTO;
import br.unifor.pin.doaweb.utils.Navigation;

@RequestScoped
@ManagedBean(name = "listCampanha")
@Component(value = "listCampanha")
public class ListCampanhaManager {

	@Autowired
	private CampanhaBO campanhaBO;
	@Autowired
	private CampanhasDAO campanhasDAO;
	@Autowired
	private DoacaoBO doacaoBO;
	@Autowired
	private SegurancaTO segurancaTO;

	private StatusDoacao statusDoacao;
	private Date dataInicioFiltro;
	private Campanhas campanha;

	private List<Campanhas> ltCampanhas;
	private List<Doacao> ltDoacoes;

	// Inativa uma Campanha
	public void excluir(Campanhas campanha) {
//		campanhaBO.excluirCampanha(campanha);
//		ltCampanhas = campanhaBO.buscarCampPorInst(segurancaTO.getUsuario());
		campanha.setStatus(StatusCampanha.INATIVA);
	}

	// Lista as campanhas por data de íncio
	public String listarMinhasCampanhasPorFiltro() {
		ltCampanhas = campanhaBO.buscaCampanhasPorInstituicaoData(
				(Instituicoes) segurancaTO.getUsuario(), dataInicioFiltro);
		return Navigation.LISTCAMPINST;
	}

	// Lista as doação que a Campanha possui
	public String listarDoacaoesNaCampanha(Campanhas doaCamp) {
		ltDoacoes = doacaoBO.buscarDoacPorCamp(doaCamp);
		return Navigation.LISTDOANACAMP;
	}

	// Lista as campanhas da Instituição e atualiza o status da campanha
	public String listarMinhasCampanhas() {
		campanhaBO.setStatusCampanhas();
		ltCampanhas = campanhaBO.buscarCampPorInst(segurancaTO.getUsuario());
		return Navigation.LISTCAMPINST;
	}

	/*
	 * GETTS AND SETTS
	 */

	public List<Campanhas> getLtCampanhas() {
		return ltCampanhas;
	}

	public void setLtCampanhas(List<Campanhas> ltCampanhas) {
		this.ltCampanhas = ltCampanhas;
	}

	public Campanhas getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanhas campanha) {
		this.campanha = campanha;
	}

	public Date getDataInicioFiltro() {
		return dataInicioFiltro;
	}

	public void setDataInicioFiltro(Date dataInicioFiltro) {
		this.dataInicioFiltro = dataInicioFiltro;
	}

	public List<Doacao> getLtDoacoes() {
		return ltDoacoes;
	}

	public void setLtDoacoes(List<Doacao> ltDoacoes) {
		this.ltDoacoes = ltDoacoes;
	}

	public StatusDoacao getStatusDoacao() {
		return statusDoacao;
	}

	public void setStatusDoacao(StatusDoacao statusDoacao) {
		this.statusDoacao = statusDoacao;
	}

}
