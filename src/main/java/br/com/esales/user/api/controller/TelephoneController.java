package br.com.esales.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esales.user.core.validation.Groups;
import br.com.esales.user.domain.exception.BusinessException;
import br.com.esales.user.domain.exception.EntityNotFoundedException;
import br.com.esales.user.domain.model.Telephone;
import br.com.esales.user.domain.repository.TelephoneRepository;
import br.com.esales.user.domain.service.TelephoneService;

@RestController
@RequestMapping(path = "api/telephone")
public class TelephoneController {

	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@Autowired
	private TelephoneService telephoneService;
	
	@PostMapping("/save-telephone")
	
	public Telephone created(@RequestBody @Validated( Groups.RegisterTelephone.class) Telephone telephone) {
		try {
			return telephoneService.save(telephone);
		} catch (EntityNotFoundedException e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
