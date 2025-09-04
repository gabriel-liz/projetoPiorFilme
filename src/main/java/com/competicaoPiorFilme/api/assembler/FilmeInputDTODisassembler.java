package com.competicaoPiorFilme.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competicaoPiorFilme.api.model.input.FilmeInputDTO;
import com.competicaoPiorFilme.domain.model.Estudio;
import com.competicaoPiorFilme.domain.model.Filme;
import com.competicaoPiorFilme.domain.model.Produtor;
import com.competicaoPiorFilme.domain.repository.EstudioRepository;
import com.competicaoPiorFilme.domain.repository.ProdutorRepository;

@Component
public class FilmeInputDTODisassembler {
	
	@Autowired
	private ProdutorRepository produtorRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	
	   public FilmeInputDTODisassembler(ProdutorRepository produtorRepository, EstudioRepository estudioRepository) {
	        this.produtorRepository = produtorRepository;
	        this.estudioRepository = estudioRepository;
	    }
	   
	   public Filme toDomainObject(FilmeInputDTO input) {
	        Filme filme = new Filme();
	        filme.setTitulo(input.getTitulo());
	        filme.setAno(input.getAno());

	        List<Produtor> produtores = input.getProdutoresIds().stream()
	            .map(id -> produtorRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Produtor com ID " + id + " não encontrado")))
	            .collect(Collectors.toList());

	        List<Estudio> estudios = input.getEstudiosIds().stream()
	            .map(id -> estudioRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Estúdio com ID " + id + " não encontrado")))
	            .collect(Collectors.toList());

	        filme.setProdutores(produtores);
	        filme.setEstudios(estudios);

	        return filme;
	    }

	
}
