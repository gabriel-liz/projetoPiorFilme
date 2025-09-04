package com.competicaoPiorFilme.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competicaoPiorFilme.api.model.ProdutorDTO;
import com.competicaoPiorFilme.domain.model.Produtor;

@Component
public class ProdutorDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ProdutorDTO toDTO(Produtor produtor) {
		return modelMapper.map(produtor, ProdutorDTO.class);
	}
	
	public List<ProdutorDTO> toCollectionDTO(List<Produtor> produtores){
		return produtores.stream()
				.map(produtor -> toDTO(produtor))
				.collect(Collectors.toList());
	}
}
