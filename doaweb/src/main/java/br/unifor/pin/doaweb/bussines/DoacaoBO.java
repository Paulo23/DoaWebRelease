package br.unifor.pin.doaweb.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.aspectj.Loggable;
import br.unifor.pin.doaweb.aspectj.PermitAll;
import br.unifor.pin.doaweb.dao.CampanhasDAO;
import br.unifor.pin.doaweb.dao.DoacaoDAO;
import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Doacao;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.StatusDoacao;
import br.unifor.pin.doaweb.exceptions.BOException;

@Component
public class DoacaoBO {

	@Autowired
	private DoacaoDAO doacaoDAO;
	
	@Autowired
	private CampanhasDAO campanhaDAO;

	public void salvar(Doacao doacao) throws BOException {
		
		Campanhas campanha = this.campanhaDAO.buscaCampanhaPorId(doacao.getCampanha().getId());
		if(StatusCampanha.FECHADA.equals(campanha.getStatus())){
			throw new BOException("Já passou o prazo de doação para esta campanha.");
		}
		
		doacaoDAO.salvar(doacao);
	}

	// Todas as doações de um doador
	@Loggable(enable = false)
	public List<Doacao> buscarDoacPorDoad(Usuarios doador) {
		return doacaoDAO.buscaDoacaoPorDoador(doador);
	}

	@PermitAll
	@Loggable(enable = false)
	public List<Doacao> buscarDoacPorIdDoador(Integer id) {
		return doacaoDAO.buscaDoacaoPorIdDoador(id);
	}

	// Todas as doações de uma campanha
	@Loggable(enable = false)
	public List<Doacao> buscarDoacPorCamp(Campanhas camp) {
		return doacaoDAO.buscaDoacaoPorCampanha(camp);
	}
	
	@Loggable(enable = false)
	public List<Doacao> buscarDoacPorCampEStatus(Campanhas camp, StatusDoacao doacao) {
		return doacaoDAO.buscaDoacaoPorCampanhaEStatus(camp, doacao);
	}
	
	@Loggable(enable = false)
	public List<Doacao> buscaDoacaoPorStatus(StatusDoacao doacao) {
		return doacaoDAO.buscaDoacaoPorStatus(doacao);
	}

	// Alterar status da doação
	public void alteraStatusDoacao(Doacao doacao, StatusDoacao statusDoacao) {
		Doacao doacao2 = doacaoDAO.buscaDoacaoPorId(doacao.getId());
		doacao2.setStatus(statusDoacao);
		doacaoDAO.atualizar(doacao2);
	}

	// Alterar status da doação
	public void alteraStatusVisualizada(Doacao doacao) {
		doacao.setStatus(StatusDoacao.VISUALIZADA);
		doacaoDAO.atualizar(doacao);
	}

	// Alterar status da doação
	public void alteraStatusRecebida(Doacao doacao) throws BOException {
		try {
			doacao.setStatus(StatusDoacao.RECEBIDA);
			doacaoDAO.atualizar(doacao);
		} catch (Exception e) {
			throw new BOException("msg de erro aqui");
		}
	}
}
