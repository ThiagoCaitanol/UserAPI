package br.com.esales.user.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.esales.user.domain.model.User;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long>{

	List<User> findAllByNameContaining(String name);
	
	
}
