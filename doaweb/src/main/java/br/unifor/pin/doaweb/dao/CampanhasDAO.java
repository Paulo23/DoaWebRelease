package br.unifor.pin.doaweb.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import br.unifor.pin.doaweb.entity.Campanhas;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.enums.StatusCampanha;
import br.unifor.pin.doaweb.enums.TipoDoacao;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class CampanhasDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Campanhas campanha) {
		entityManager.persist(campanha);
	}

	public void atualizar(Campanhas campanha) {
		entityManager.merge(campanha);
	}

	public void excluir(Campanhas campanha) {
		entityManager.remove(campanha);
	}

	// Busca todas as campanhas de uma determinada instituição
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscaCampanhasPorInstituicao(Usuarios instituicao) {
		String jpql = "select u from Campanhas u where u.instituicao = :instituicao "
				+ "and u.status != :status order by u.status, u.dataTerminoCampanhas";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("instituicao", instituicao);
		query.setParameter("status", StatusCampanha.INATIVA);
		return (List<Campanhas>) query.getResultList();
	}

	// Busca pelo Id uma determinada campanha
	public Campanhas buscaCampanhaPorId(Integer id) {
		String jpql = "select u from Campanhas u where u.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		return (Campanhas) query.getSingleResult();
	}

	// Busca todas as campanhas do banco
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscaTodasCampanhas() {
		String jpql = "select c from Campanhas c order by c.status";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	// Busca todas as campanhas ativas
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscarCampanhasAtivas() {
		String jpql = "select c from Campanhas c where c.status = 0";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	// Filtra por data as campanhas de uma determinada instituição
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscaCampanhasPorInstituicaoData(Instituicoes instituicao, Date data) {
		String jpql = "select c from Campanhas c where c.instituicao = :instituicao "
				+ "and c.dataInicioCampanhas = :data and c.status != :status ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("instituicao", instituicao);
		query.setParameter("data", data);
		query.setParameter("status", StatusCampanha.INATIVA);
		return (List<Campanhas>) query.getResultList();
	}

	// Filtra campanha por tipo(Alimentos, Dinheiro, Roupas)
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscaCampanhasPorTipo(TipoDoacao doacao) {
		String jpql = "select c from Campanhas c where c.tipo = :doacao and c.status = 0";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("doacao", doacao);
		return (List<Campanhas>) query.getResultList();
	}
	
	// Filtra campanha por tipo(Alimentos, Dinheiro, Roupas)
	@SuppressWarnings("unchecked")
	public List<Campanhas> buscaCampanhasPorTipoInstituicao(TipoDoacao doacao) {
		String jpql = "select c from Campanhas c where c.tipo = :doacao";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("doacao", doacao);
		return (List<Campanhas>) query.getResultList();
	}
}
