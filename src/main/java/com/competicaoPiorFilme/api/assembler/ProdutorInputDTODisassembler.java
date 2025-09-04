package com.competicaoPiorFilme.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.competicaoPiorFilme.api.model.input.ProdutorInputDTO;
import com.competicaoPiorFilme.domain.model.Produtor;

@Component
public class ProdutorInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Produtor toDomainObect(ProdutorInputDTO produtorInputDTO) {
		return modelMapper.map(produtorInputDTO, Produtor.class);
	}
	
	public void copyToDomainObject(ProdutorInputDTO produtorInputDTO, Produtor produtor) {
		modelMapper.map(produtorInputDTO, produtor);
	}
}
