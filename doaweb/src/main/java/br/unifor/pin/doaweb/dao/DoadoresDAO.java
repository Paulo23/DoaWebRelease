package br.unifor.pin.doaweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.doaweb.entity.Doadores;
import br.unifor.pin.doaweb.exceptions.DAOException;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DoadoresDAO {

	@PersistenceContext
	private EntityManager entityManager;

	//Busca por Id um determinado doador
	public Doadores buscaPorId(Integer id) throws DAOException {
		String jpql = "select u from Doadores u where u.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		try {
			return (Doadores) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//busca por cpf um determinado doador
	public Doadores buscarPorCPF(String cpf) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Doadores> criteriaQuery = criteriaBuilder
				.createQuery(Doadores.class);
		Root<Doadores> Doadores = criteriaQuery.from(Doadores.class);
		criteriaQuery.where(criteriaBuilder.equal(
				Doadores.<String> get("cpf"), cpf));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Doadores) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//Busca por email um determinado doador
	public Doadores buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Doadores> criteriaQuery = criteriaBuilder
				.createQuery(Doadores.class);
		Root<Doadores> Doadores = criteriaQuery.from(Doadores.class);
		criteriaQuery.where(criteriaBuilder.equal(
				Doadores.<String> get("email"), email));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Doadores) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//Busca por email e senha um determinado doador
	public Doadores buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Doadores> criteriaQuery = criteriaBuilder
				.createQuery(Doadores.class);
		Root<Doadores> Doadores = criteriaQuery.from(Doadores.class);
		Predicate restriction = criteriaBuilder.and(
				criteriaBuilder.equal(Doadores.<String> get("email"), email),
				criteriaBuilder.equal(Doadores.<String> get("senha"), senha));
		criteriaQuery.where(criteriaBuilder.and(restriction));

		Query query = entityManager.createQuery(criteriaQuery);
		try {
			return (Doadores) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//Busca por nome um determinado doador
	@SuppressWarnings("unchecked")
	public List<Doadores> listarPorNome(String nome) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Doadores> criteriaQuery = criteriaBuilder
				.createQuery(Doadores.class);
		Root<Doadores> Doadores = criteriaQuery.from(Doadores.class);
		criteriaQuery.where(criteriaBuilder.like(Doadores.<String> get("nome"),
				"%" + nome + "%"));

		Query query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}


}
