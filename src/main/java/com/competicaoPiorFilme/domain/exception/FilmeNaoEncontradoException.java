package com.competicaoPiorFilme.domain.exception;

public class FilmeNaoEncontradoException extends EntidadeNaoEncontradaException{

	
	private static final long serialVersionUID = 1L;

	public FilmeNaoEncontradoException(String message) {
		super(message);		
	}
	
	public FilmeNaoEncontradoException(Long filmeId) {
		this(String.format("Não existe cadastro de filme com código %d", filmeId));
	}

}
