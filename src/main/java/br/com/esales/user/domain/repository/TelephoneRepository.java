package br.com.esales.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esales.user.domain.model.Telephone;

public interface TelephoneRepository extends JpaRepository<Telephone, Long>{

	
}
