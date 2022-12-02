package br.com.esales.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esales.user.domain.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

	
}
