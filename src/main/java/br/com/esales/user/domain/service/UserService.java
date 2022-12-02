package br.com.esales.user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.esales.user.domain.exception.EntityInUseException;
import br.com.esales.user.domain.exception.UserNotFoundedException;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.model.User;
import br.com.esales.user.domain.repository.AddressRepository;
import br.com.esales.user.domain.repository.TelephoneRepository;
import br.com.esales.user.domain.repository.UserRepository;

@Service
public class UserService {

	private static final String MSG_USER_IN_USE = 
			"Usuario de código %d não pode ser removido pois esta em uso";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TelephoneRepository telephoneRepository;
	

	
	@Autowired
	private AddressRepository addreessRepository;
	

	
	
	public User save(User user) {
		
		return userRepository.save(user);
	}
	
	public void delete(Long userId) {
		try {
			userRepository.deleteById(userId);
		} catch(EmptyResultDataAccessException e) {
			throw new UserNotFoundedException(userId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_USER_IN_USE, userId));
		}
	}
	
	public User searchUser(Long userId) {		
		return userRepository.findById(userId).orElseThrow(() -> 
		new UserNotFoundedException(userId)); 
				
	}
}
