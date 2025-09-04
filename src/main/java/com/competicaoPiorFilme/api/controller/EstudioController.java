package com.competicaoPiorFilme.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.competicaoPiorFilme.api.assembler.EstudioDTOAssembler;
import com.competicaoPiorFilme.api.assembler.EstudioInputDTODisassembler;
import com.competicaoPiorFilme.api.model.EstudioDTO;
import com.competicaoPiorFilme.api.model.input.EstudioInputDTO;
import com.competicaoPiorFilme.domain.model.Estudio;
import com.competicaoPiorFilme.domain.repository.EstudioRepository;
import com.competicaoPiorFilme.domain.service.EstudioService;

@RestController
@RequestMapping("/estudios")
public class EstudioController {
	
	@Autowired
	private EstudioService estudioService;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@Autowired
	private EstudioDTOAssembler estudioDTOAssembler;
	
	@Autowired
	private EstudioInputDTODisassembler estudioInputDTODisassembler;
	
	@GetMapping
	public List<EstudioDTO> listar(){
		List<Estudio> todosEstudios = estudioRepository.findAll();
		return estudioDTOAssembler.toCollectionDTO(todosEstudios);
	}
	
	@GetMapping("/{estudioId}")
	public EstudioDTO buscar(@PathVariable Long estudioId) {
		Estudio estudio = estudioService.buscarOuFalhar(estudioId);
		return estudioDTOAssembler.toDTO(estudio);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstudioDTO adicionar(@RequestBody @Valid EstudioInputDTO estudioInputDTO) {
		
		Estudio estudio = estudioInputDTODisassembler.toDomainObject(estudioInputDTO);
		estudio = estudioService.salvar(estudio);
		
		return estudioDTOAssembler.toDTO(estudio);
	}	
	
}
