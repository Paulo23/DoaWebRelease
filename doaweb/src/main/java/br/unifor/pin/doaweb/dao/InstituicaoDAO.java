package br.unifor.pin.doaweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.exceptions.DAOException;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class InstituicaoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	//Busca por Id uma determinada instituição 
	public Instituicoes buscaPorId(Integer id) throws DAOException {
		String jpql = "select u from Instituicoes u where u.id = :id";
		Query query = (Query) entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Instituicoes) ((javax.persistence.Query) query)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	
	//Busca instituição por email uma determinada 
	public Instituicoes buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instituicoes> criteriaQuery = criteriaBuilder
				.createQuery(Instituicoes.class);
		Root<Instituicoes> Instituicoes = criteriaQuery
				.from(Instituicoes.class);
		criteriaQuery.where(criteriaBuilder.equal(
				Instituicoes.<String> get("email"), email));

		Query query = (Query) entityManager.createQuery(criteriaQuery);
		try {
			return (Instituicoes) ((javax.persistence.Query) query)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//Busca por email e senha uma determinada instituição 
	public Instituicoes buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instituicoes> criteriaQuery = criteriaBuilder
				.createQuery(Instituicoes.class);
		Root<Instituicoes> Instituicoes = criteriaQuery
				.from(Instituicoes.class);
		Predicate restriction = criteriaBuilder.and(criteriaBuilder.equal(
				Instituicoes.<String> get("email"), email), criteriaBuilder
				.equal(Instituicoes.<String> get("senha"), senha));
		criteriaQuery.where(criteriaBuilder.and(restriction));

		Query query = (Query) entityManager.createQuery(criteriaQuery);
		try {
			return (Instituicoes) ((javax.persistence.Query) query)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//busca por nome uma determinada instituição
	@SuppressWarnings("unchecked")
	public List<Instituicoes> listarPorNome(String nome) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instituicoes> criteriaQuery = criteriaBuilder
				.createQuery(Instituicoes.class);
		Root<Instituicoes> Instituicoes = criteriaQuery
				.from(Instituicoes.class);
		criteriaQuery.where(criteriaBuilder.like(
				Instituicoes.<String> get("nome"), "%" + nome + "%"));

		Query query = (Query) entityManager.createQuery(criteriaQuery);
		return ((TypedQuery<br.unifor.pin.doaweb.entity.Instituicoes>) query)
				.getResultList();
	}

}
