package br.unifor.pin.doaweb.manager.doador;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.DoacaoBO;
import br.unifor.pin.doaweb.entity.Doacao;
import br.unifor.pin.doaweb.enums.StatusDoacao;
import br.unifor.pin.doaweb.exceptions.BOException;
import br.unifor.pin.doaweb.to.SegurancaTO;
import br.unifor.pin.doaweb.utils.MessagesUtils;
import br.unifor.pin.doaweb.utils.Navigation;

@RequestScoped
@ManagedBean(name = "listDoacReal")
@Component(value = "listDoacReal")
public class ListDoacRealiManager {

	@Autowired
	private DoacaoBO doacaoBO;
	@Autowired
	private SegurancaTO segurancaTO;

	private String nomeDoador;
	private String telefoneDoador;
	private String endDoador;
	private String obsDoDoaodr;
	private Boolean recebido;
	private Doacao doacao;

	private List<Doacao> ltDoacoes;

	public String atualizar() {
		if (recebido) {
			try {
				doacaoBO.alteraStatusRecebida(doacao);
				MessagesUtils.info("msg de sucesso aqui");
			} catch (BOException e) {
				MessagesUtils.error("mensagem de erro aqui");
				return Navigation.FRACASSO;
			}
		} else {
			statusVisualizada(doacao);
		}
		return Navigation.ATUALIZA;
	}

	// Doações realizadas pelo doador
	public String listarMinhasDoacRealizadas() {
		ltDoacoes = doacaoBO.buscarDoacPorDoad(segurancaTO.getUsuario());
		return Navigation.LISTDOACDOAD;
	}

	// Dados do doador
	public String preparaInforDoDoador(Doacao doacao) {
		nomeDoador = doacao.getDoador().getNome();
		telefoneDoador = doacao.getDoador().getTelDoador();
		obsDoDoaodr = doacao.getInformacoes();
		endDoador = doacao.getDoador().getEndereco();
		this.doacao = doacao;
		if(doacao.getStatus().equals(StatusDoacao.PENDENTE)){
			statusVisualizada(doacao);		
		}
		return Navigation.SUCESSO;
	}

//	public String statusRecebida(Doacao doacao) {
//		doacaoBO.alteraStatusRecebida(doacao);
//		return Navigation.VOLTAR;
//	}

	public void statusVisualizada(Doacao doacao) {
		doacaoBO.alteraStatusVisualizada(doacao);
	}

	public String voltar() {
		if (doacao.getStatus().equals(StatusDoacao.RECEBIDA)) {
			setRecebido(true);
		} else {
			setRecebido(false);
		}
		return Navigation.VOLTAR;
	}

	/*
	 * GETTS AND SETTS
	 */

	public List<Doacao> getLtDoacoes() {
		return ltDoacoes;
	}

	public void setLtDoacoes(List<Doacao> ltDoacoes) {
		this.ltDoacoes = ltDoacoes;
	}

	public String getNomeDoador() {
		return nomeDoador;
	}

	public void setNomeDoador(String nomeDoador) {
		this.nomeDoador = nomeDoador;
	}

	public String getTelefoneDoador() {
		return telefoneDoador;
	}

	public void setTelefoneDoador(String telefoneDoador) {
		this.telefoneDoador = telefoneDoador;
	}

	public String getObsDoDoaodr() {
		return obsDoDoaodr;
	}

	public void setObsDoDoaodr(String obsDoDoaodr) {
		this.obsDoDoaodr = obsDoDoaodr;
	}

	public String getEndDoador() {
		return endDoador;
	}

	public void setEndDoador(String endDoador) {
		this.endDoador = endDoador;
	}

	public Boolean getRecebido() {
		return recebido;
	}

	public void setRecebido(Boolean recebido) {
		this.recebido = recebido;
	}

}
