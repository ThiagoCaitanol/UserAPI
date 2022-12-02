package br.com.esales.user.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.esales.user.domain.exception.EntityInUseException;
import br.com.esales.user.domain.exception.EntityNotFoundedException;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.model.User;
import br.com.esales.user.domain.repository.TelephoneRepository;
import br.com.esales.user.domain.repository.UserRepository;

@Service
public class TelephoneService {
	
	private static final String MSG_USER_IN_USE = 
			"Telefone de código %d não pode ser removido pois esta em uso";
	private static final String MSG_USER_NOT_FOUND = 
			"Não existe um cadastro de telegone com codigo %d";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	
	public Telephone save(Telephone telephone) {
		Long userId = telephone.getUser().getId();
		
		User user = userService.searchUser(userId);
		
		telephone.setUser(user);
		
		return telephoneRepository.save(telephone);
	}
	
	public void delete(Long telephoneId) {
		try {
			telephoneRepository.deleteById(telephoneId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundedException(
					String.format(MSG_USER_NOT_FOUND, telephoneId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_USER_IN_USE, telephoneId));
		}
	}
	
	public Telephone searchTelephone(Long telephoneId) {
		return telephoneRepository.findById(telephoneId)
				.orElseThrow(() -> new EntityNotFoundedException(
				String.format(MSG_USER_IN_USE, telephoneId))); 
				
	}


}
