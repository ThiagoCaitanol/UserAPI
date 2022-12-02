package br.com.esales.user.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundedException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundedException(String message) {
		super(message);
	}

}
