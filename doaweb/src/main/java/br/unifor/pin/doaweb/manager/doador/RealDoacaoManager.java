package br.unifor.pin.doaweb.manager.doador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.CampanhaBO;
import br.unifor.pin.doaweb.bussines.DoacaoBO;
import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Doacao;
import br.unifor.pin.doaweb.entity.Doadores;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.StatusDoacao;
import br.unifor.pin.doaweb.to.SegurancaTO;
import br.unifor.pin.doaweb.utils.MessagesUtils;
import br.unifor.pin.doaweb.utils.Navigation;

@RequestScoped
@ManagedBean(name = "realizarDoac")
@Component(value = "realizarDoac")
public class RealDoacaoManager {

	@Autowired
	private CampanhaBO campanhaBO;

	@Autowired
	private SegurancaTO segurancaTO;

	@Autowired
	private DoacaoBO doacaoBO;

	private String nomeInstituicao;
	private String descricao;
	private String tipo;
	private String obs;

	private String nomeBanco;
	private String ag;
	private String op;
	private String conta;

	private Double valorDac;

	private Campanhas campanhas;
	private Campanhas camps;
	private List<Campanhas> ltTodasCampanhas;

	// Todas as campanhas do banco
	public String listarTodasCampanhas() {
		ltTodasCampanhas = campanhaBO.buscarTodasCamp();
		return Navigation.LISTCAMPDOAD;
	}

	// Dados da instituição
	public String preparaDoar(Campanhas camp) {
		if (camp.getStatus().equals(StatusCampanha.INATIVA)) {
		MessagesUtils.error("Campanha Inativa, escolha outra!");
		return Navigation.FRACASSO;
		} 
			nomeInstituicao = camp.getInstituicao().getRazaoSocial();
			descricao = camp.getDescricao();
			tipo = camp.getTipo().toString();

			nomeBanco = camp.getInstituicao().getNomeBanco();
			ag = camp.getInstituicao().getAgencia();
			op = camp.getInstituicao().getOperacao();
			conta = camp.getInstituicao().getConta();

			setCamps(camp);
		return Navigation.SUCESSO;

	}

	public String doar() {
		Doacao doacao = new Doacao();

		doacao.setCampanha(getCamps());
		doacao.setDoador((Doadores) segurancaTO.getUsuario());
		doacao.setDataDoacao(getCamps().getDataInicioCampanhas());
		doacao.setTipoDeDoacao(getCamps().getTipo());
		doacao.setInformacoes(getObs());
		doacao.setStatus(StatusDoacao.PENDENTE);

		try {
			this.doacaoBO.salvar(doacao);
			limpaDados();
			MessagesUtils.info("Doação realizada com sucesso");
		} catch (Exception e) {
			MessagesUtils.error(e.getMessage());
		}
		return Navigation.SUCESSO;
	}

	public String voltar() {
		this.obs = "";
		return Navigation.VOLTAR;
	}

	public void limpaDados() {
		this.obs = "";
	}

	/*
	 * GETTS AND SETTS
	 */

	public List<Campanhas> getLtTodasCampanhas() {
		return ltTodasCampanhas;
	}

	public void setLtTodasCampanhas(List<Campanhas> ltTodasCampanhas) {
		this.ltTodasCampanhas = ltTodasCampanhas;
	}

	public Campanhas getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(Campanhas campanhas) {
		this.campanhas = campanhas;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Campanhas getCamps() {
		return camps;
	}

	public void setCamps(Campanhas camps) {
		this.camps = camps;
	}

	public Double getValorDac() {
		return valorDac;
	}

	public void setValorDac(Double valorDac) {
		this.valorDac = valorDac;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getAg() {
		return ag;
	}

	public void setAg(String ag) {
		this.ag = ag;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

}
