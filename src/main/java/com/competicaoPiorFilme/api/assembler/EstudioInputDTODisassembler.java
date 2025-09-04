package com.competicaoPiorFilme.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competicaoPiorFilme.api.model.input.EstudioInputDTO;
import com.competicaoPiorFilme.domain.model.Estudio;

@Component
public class EstudioInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Estudio toDomainObject(EstudioInputDTO estudioInputDTO) {
		return modelMapper.map(estudioInputDTO, Estudio.class);
	}
	
	public void copyToDomainObject(EstudioInputDTO estudioInputDTO, Estudio estudio) {
		modelMapper.map(estudioInputDTO, estudio);
	}
}
