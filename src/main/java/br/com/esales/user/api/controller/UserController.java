package br.com.esales.user.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.esales.user.domain.exception.BusinessException;
import br.com.esales.user.domain.exception.EntityNotFoundedException;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.model.User;
import br.com.esales.user.domain.repository.UserRepository;
import br.com.esales.user.domain.service.TelephoneService;
import br.com.esales.user.domain.service.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> ray(User user){
		
		return userRepository.filter(user);
	}
	
	@GetMapping("/{userId}")
	public User search(@PathVariable Long userId) {
		return userService.searchUser(userId);
	}
	

	
	@GetMapping("/by-document")
	public Optional<User> consultByDocument(String cpfCnpj) {
		return userRepository.searchForDocument(cpfCnpj);
	}
	
	@GetMapping("/by-name")
	public List<User> userByName(String name) {
		return userRepository.findAllByNameContaining(name);
	}

	@PostMapping("/save-user")
	@ResponseStatus(HttpStatus.CREATED)
	public User created(@RequestBody @Valid User user) {
		try {
			
			user.setStatus(true);
			return userService.save(user);			
		} catch (EntityNotFoundedException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PutMapping("/{userId}")
	public User update(@PathVariable Long userId,
			@RequestBody User user) {
		User currentUser = userService.searchUser(userId);
		
		BeanUtils.copyProperties(user, currentUser, "id", "cpfCnpj", "personType", "status");
			
		try {			
			return userService.save(currentUser);
		} catch (EntityNotFoundedException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	@PutMapping("/status/{userId}") 
	public ResponseEntity<User> updateStatus(@PathVariable Long userId,
			@RequestBody User user) {
		Optional<User> currentUser = userRepository.findById(userId);
		
		if(currentUser.isPresent()) {
			BeanUtils.copyProperties(user, currentUser.get(), "id", "name", "email", "personType", "cpfCnpj");		
			
			User savedUser = userService.save(currentUser.get());
			return ResponseEntity.ok(savedUser);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{userId}")
	public User parcialAtualizate(@PathVariable Long userId,
			@RequestBody Map<String, Object> inserts, HttpServletRequest request) {
		User currentUser = userService.searchUser(userId);
		
		merge(inserts, currentUser, request);
		
		return update(userId, currentUser);
	}
	
	private void merge(Map<String, Object> dataOrigin, User userDestiny, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request); 
		
		try {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		
		User userOrigin = objectMapper.convertValue(dataOrigin, User.class);
		
		dataOrigin.forEach((propertieName, propertieValue) -> {
			Field field = ReflectionUtils.findField(User.class, propertieName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, userOrigin);
			
			ReflectionUtils.setField(field, userDestiny, newValue);
		});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	
	}
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long userId) {
		userService.delete(userId);
	}
	

	
}
