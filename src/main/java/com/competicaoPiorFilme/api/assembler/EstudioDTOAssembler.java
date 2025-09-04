package com.competicaoPiorFilme.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competicaoPiorFilme.api.model.EstudioDTO;
import com.competicaoPiorFilme.domain.model.Estudio;

@Component
public class EstudioDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstudioDTO toDTO(Estudio estudio) {
		return modelMapper.map(estudio, EstudioDTO.class);
	}
	
	public List<EstudioDTO> toCollectionDTO(List<Estudio> estudios){
		return estudios.stream()
				.map(estudio -> toDTO(estudio))
				.collect(Collectors.toList());
	}
}
