package com.competicaoPiorFilme.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competicaoPiorFilme.domain.exception.ProdutorNaoEncontradoException;
import com.competicaoPiorFilme.domain.model.Produtor;
import com.competicaoPiorFilme.domain.repository.ProdutorRepository;

@Service
public class ProdutorService {
	
	
	@Autowired
	ProdutorRepository produtorRepository;
	
	@Transactional
	public Produtor salvar(Produtor producer) {
		return produtorRepository.save(producer);
	}	
	
	public Produtor buscarOuFalhar(Long produtorId) {
		return produtorRepository.findById(produtorId)
				.orElseThrow(() -> new ProdutorNaoEncontradoException(produtorId));
	}
	
}
