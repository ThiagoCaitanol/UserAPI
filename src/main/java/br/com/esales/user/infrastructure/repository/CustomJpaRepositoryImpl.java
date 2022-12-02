package br.com.esales.user.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.esales.user.domain.model.Address;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.model.User;
import br.com.esales.user.domain.repository.CustomJpaRepository;
import lombok.var;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
	implements CustomJpaRepository<T, ID> {

	
	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
		this.manager = entityManager;
	}

	@Override
	public Optional<T> searchForDocument(String cpfCnpj){
		var jpql = "from " + getDomainClass().getName() + " where cpfCnpj is '" + cpfCnpj + "'";
		
		T entity = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();
				
		return Optional.ofNullable(entity);
	}
	
	@Override
	public List<User> filter(User filter) {
		
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            predicates.add(cb.like(root.get("name"), filter.getName()));
        }
        if (filter.getCpfCnpj() != null) {
            predicates.add(cb.like(root.get("cpfCnpj"), filter.getCpfCnpj()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<User> tq = manager.createQuery(cq);


        return tq.getResultList();
    }
}
