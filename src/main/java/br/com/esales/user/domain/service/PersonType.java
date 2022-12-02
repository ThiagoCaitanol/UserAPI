package br.com.esales.user.domain.service;

import lombok.Getter;

@Getter
public enum PersonType {

	NATURAL("Física", "CPF", "000.000.000-00", CpfGroup.class),
	LEGALPERSON("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private final String description;
	private final String document;
	private final String masck;
	private final Class<?> group;
	
	private PersonType(String description, String document, String masck, Class<?> group) {
		this.description = description;
		this.document = document;
		this.masck = masck;
		this.group = group;
	}
}
