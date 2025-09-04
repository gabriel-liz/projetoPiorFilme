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

import com.competicaoPiorFilme.api.assembler.ProdutorDTOAssembler;
import com.competicaoPiorFilme.api.assembler.ProdutorInputDTODisassembler;
import com.competicaoPiorFilme.api.model.ProdutorDTO;
import com.competicaoPiorFilme.api.model.input.ProdutorInputDTO;
import com.competicaoPiorFilme.domain.model.Produtor;
import com.competicaoPiorFilme.domain.repository.ProdutorRepository;
import com.competicaoPiorFilme.domain.service.ProdutorService;

@RestController
@RequestMapping("/produtores")
public class ProdutorController {
	
	@Autowired
	ProdutorService produtorService;
	
	@Autowired
	private ProdutorRepository produtorRepository;
	
	@Autowired
	private ProdutorDTOAssembler produtorDTOAssembler;
	
	@Autowired
	private ProdutorInputDTODisassembler produtorInputDTODisassembler;
	
	@GetMapping
	public List<ProdutorDTO> listar(){
		List<Produtor> todosProdutores = produtorRepository.findAll();
		return produtorDTOAssembler.toCollectionDTO(todosProdutores);
	}
	
	@GetMapping("/{produtorId}")
	public ProdutorDTO buscar(@PathVariable Long produtorId) {
		
		Produtor produtor = produtorService.buscarOuFalhar(produtorId);
		return produtorDTOAssembler.toDTO(produtor);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutorDTO adicionar(@RequestBody @Valid ProdutorInputDTO produtorInputDTO) {
		
		Produtor produtor = produtorInputDTODisassembler.toDomainObect(produtorInputDTO);
		produtor = produtorService.salvar(produtor);
		
		return produtorDTOAssembler.toDTO(produtor);
	}
	
	

}
