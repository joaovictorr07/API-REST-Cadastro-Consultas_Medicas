package com.teste.demo.services.exceptions;

public class NomeDataException extends Exception {

	private static final long serialVersionUID = -1402677565107062800L;
	
	public NomeDataException() {
	super("Consulta já agendada para esse paciente na data informada");
	}

}
