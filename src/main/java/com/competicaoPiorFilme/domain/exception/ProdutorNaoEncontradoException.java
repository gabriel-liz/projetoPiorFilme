package com.competicaoPiorFilme.domain.exception;

public class ProdutorNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public ProdutorNaoEncontradoException(String message) {
		super(message);
	}
	
	public ProdutorNaoEncontradoException(Long producerId) {
		this(String.format("Não existe cadastro de produtor com código %d", producerId));
	}

}
