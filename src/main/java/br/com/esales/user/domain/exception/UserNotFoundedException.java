package br.com.esales.user.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundedException extends EntityNotFoundedException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundedException(String message) {
		super(message);
	}

	public UserNotFoundedException(Long userId) {
		this(String.format("Não existe um cadastro de usuario com codigo %d", userId));
	}
}
