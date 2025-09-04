package com.competicaoPiorFilme.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.competicaoPiorFilme.domain.exception.EstudioNaoEncontradoException;
import com.competicaoPiorFilme.domain.model.Estudio;
import com.competicaoPiorFilme.domain.repository.EstudioRepository;

@Service
public class EstudioService {
	
	@Autowired
	EstudioRepository estudioRepository;
	
	@Transactional
	public Estudio salvar(Estudio estudio) {
		return estudioRepository.save(estudio);
	}
	
	public Estudio buscarOuFalhar(Long estudioId) {
		return estudioRepository.findById(estudioId)
				.orElseThrow(() -> new EstudioNaoEncontradoException(estudioId));
	}
}
