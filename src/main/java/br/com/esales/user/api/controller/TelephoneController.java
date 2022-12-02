package br.com.esales.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.esales.user.domain.repository.TelephoneRepository;
import br.com.esales.user.domain.service.TelephoneService;

@RestController
@RequestMapping(path = "api/telephone")
public class TelephoneController {

	@Autowired
	private TelephoneRepository telephoneRepository;
	
	@Autowired
	private TelephoneService telephoneService;
	

}
