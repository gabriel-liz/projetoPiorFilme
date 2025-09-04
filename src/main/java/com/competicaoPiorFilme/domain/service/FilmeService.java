package com.competicaoPiorFilme.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competicaoPiorFilme.domain.exception.FilmeNaoEncontradoException;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.model.Produtor;
import com.competicaoPiorFilme.domain.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private ProdutorService produtorService;
	
	@Transactional
	public Filme salvar(Filme filme) {
		return filmeRepository.save(filme);		
	}
	
	@Transactional
	public void associarProdutor(Long filmeId, Long produtorId) {
		Filme filme = buscarOuFalhar(filmeId);
		Produtor produtor = produtorService.buscarOuFalhar(produtorId);		
		filme.adicionarProdutor(produtor);
	}
	
	@Transactional
	public void desassociarProdutor(Long filmeId, Long produtorId) {
		Filme filme = buscarOuFalhar(filmeId);		
		Produtor produtor = produtorService.buscarOuFalhar(produtorId);		
		filme.desassociarProdutor(produtor);
	}
	
	
	public Filme buscarOuFalhar(Long filmeId) {
		return filmeRepository.findById(filmeId)
				.orElseThrow(() -> new FilmeNaoEncontradoException(filmeId));
	}
}
