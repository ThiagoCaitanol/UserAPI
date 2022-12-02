package br.com.esales.user.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
	INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
	MESSAGE_NOT_READABLE("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTITY_NOT_FOUND("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTITY_ON_USE("/entidade-em-uso", "Entidade em uso"),
	BUSINESS_ERRO("/erro-negocio", "Violação de regra de negocio"),
	INVALID_DATA("/campo-invalido", "Campo inserido é invalido");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://acrescenteauri.com.br" + path;
		this.title = title;
	}
}
