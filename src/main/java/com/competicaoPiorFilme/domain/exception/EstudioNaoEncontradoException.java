package com.competicaoPiorFilme.domain.exception;

public class EstudioNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public EstudioNaoEncontradoException(String message) {
		super(message);		
	}
	
	public EstudioNaoEncontradoException(Long estudioId) {
		this(String.format("Não existe cadastro de produtor com código %d", estudioId));
	}

}
