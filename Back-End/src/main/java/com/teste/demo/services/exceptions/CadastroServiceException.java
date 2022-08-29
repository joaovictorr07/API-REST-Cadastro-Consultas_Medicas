package com.teste.demo.services.exceptions;

public class CadastroServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -1402677565107062800L;
	
	public CadastroServiceException(String mensagem) {
		super(mensagem);
	}
	
	public CadastroServiceException(String mensagem, Throwable causa) {
		super(mensagem,causa);
	}
	
}
