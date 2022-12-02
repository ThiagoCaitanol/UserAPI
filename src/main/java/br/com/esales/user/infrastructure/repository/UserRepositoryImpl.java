package br.com.esales.user.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	
	
//	public User findDocument(String cpfCnpj) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<User> criteria = builder.createQuery(User.class);
//		Root<User> root = criteria.from(User.class);
//		
//		Predicate predicateName = builder.like(root.get("cpfCnpj"), "%" + cpfCnpj + "%");
//		
//		criteria.where(predicateName);
//		
//		TypedQuery<User> query = manager.createQuery(criteria);
//		return query.getSingleResult();
//	}
}
