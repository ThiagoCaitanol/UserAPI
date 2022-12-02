package br.com.esales.user.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.esales.user.domain.model.Address;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.model.User;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID>{

	Optional<T> searchForDocument(String cpfCnpj);
	
	List<User> filter(User filter);
	
}
