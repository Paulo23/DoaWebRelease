package br.unifor.pin.doaweb.bussines;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.doaweb.aspectj.Loggable;
import br.unifor.pin.doaweb.aspectj.RolesAllowed;
import br.unifor.pin.doaweb.dao.CampanhasDAO;
import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.TipoDoacao;
import br.unifor.pin.doaweb.enums.TipoUsuario;

@Component
public class CampanhaBO {

	@Autowired
	private CampanhasDAO campanhasDAO;

	@RolesAllowed(value = TipoUsuario.INSTITUICAO)
	public void salvar(Campanhas campanha) {
		campanhasDAO.salvar(campanha);
	}

	@RolesAllowed(value = TipoUsuario.INSTITUICAO)
	public void atualizarCampanha(Campanhas campanha) {
		campanhasDAO.atualizar(campanha);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void excluirCampanha(Campanhas campanha) {
		campanha = campanhasDAO.buscaCampanhaPorId(campanha.getId());
		campanhasDAO.excluir(campanha);
	}

	//Todas as campanhas de uma instituição
	@Loggable(enable = false)
	public List<Campanhas> buscarCampPorInst(Usuarios instituicao) {
		return campanhasDAO.buscaCampanhasPorInstituicao(instituicao);
	}
	
	//Todas as campanhas de uma instituição filtrando por data
	public List<Campanhas> buscaCampanhasPorInstituicaoData(
			Instituicoes instituicao, Date data) {
		return campanhasDAO.buscaCampanhasPorInstituicaoData(instituicao, data);
	}
	
	public List<Campanhas> buscaCampanhasPorTipo(
			TipoDoacao doacao) {
		return campanhasDAO.buscaCampanhasPorTipo(doacao);
	}
	
	//Todas as campanhas do banco
	@Loggable(enable = false)
	public List<Campanhas> buscarTodasCamp() {
		List<Campanhas> campanhas = campanhasDAO.buscaTodasCampanhas();
		return campanhas;
	}
	
	@Loggable(enable = false)
	public List<Campanhas> buscarCampanhasAtivas() {
		List<Campanhas> campanhas = campanhasDAO.buscarCampanhasAtivas();
		return campanhas;
	}

	//Valída a data de termino da campanha
	public boolean validaDataDeTerminoDeCampanha(Date date) {
		Date date2 = new Date();
		System.out.println(date2);
		System.out.println(date);
		if (date2.before(date) && !(date2.equals(date))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void buscarCampanhaPorId(Integer id) {
		campanhasDAO.buscaCampanhaPorId(id);
	}

	
	//Muda o status da campanha para inativo
	public void setStatusCampanhas() {
		ArrayList<Campanhas> campanhas = (ArrayList<Campanhas>) campanhasDAO.buscaTodasCampanhas();
		for (Campanhas campanha : campanhas) {
			if(new Date().after(campanha.getDataTerminoCampanhas()) || StatusCampanha.INATIVA.equals(campanha.getStatus())){
				campanha.setStatus(StatusCampanha.INATIVA);
				campanhasDAO.atualizar(campanha);
			} 
		}
	}

}
